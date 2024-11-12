package henrykado.aetherbaubles.asm;

import com.gildedgames.the_aether.containers.inventory.InventoryAccessories;
import henrykado.aetherbaubles.baubles.ItemBaubles;
import henrykado.aetherbaubles.baubles.LeatherGloves;
import henrykado.aetherbaubles.util.ASMMethods;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import scala.tools.asm.Opcodes;

public class ClassTransformer implements IClassTransformer {
    static Logger LOGGER = LogManager.getLogger("aether_baubles");

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("com.gildedgames.the_aether.containers.inventory.InventoryAccessories")) {
            ClassNode classNode = new ClassNode();
            new ClassReader(basicClass).accept(classNode, ClassReader.SKIP_FRAMES);

            for (MethodNode method : classNode.methods) {
                if (method.name.equals("<init>") || method.name.equals("writeToNBT") || method.name.equals("readFromNBT") || method.name.equals("wearingArmor")) continue;

                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (node instanceof FieldInsnNode && ((FieldInsnNode)node).name.equals("stacks")) {
                        if (method.name.equals("getAccessories") || method.name.equals("isEmpty") || method.name.equals("getStackFromItem"))
                        {
                            method.instructions.insertBefore(node, new FieldInsnNode(Opcodes.GETFIELD, "com/gildedgames/the_aether/containers/inventory/InventoryAccessories", "player", "Lnet/minecraft/entity/player/EntityPlayer;"));
                            method.instructions.set(node, new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(ASMMethods.class), "getBaublesItemstackList", "(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/util/NonNullList;", false));
                        }
                        else if (!(node.getNext() instanceof MethodInsnNode))
                        {
                            method.instructions.remove(node.getPrevious()); // this.
                            method.instructions.remove(node); // stacks
                        }
                    }
                    else if (node instanceof MethodInsnNode && ((MethodInsnNode) node).owner.equals("net/minecraft/util/NonNullList")) {
                        if (((MethodInsnNode) node).name.equals("get")) {
                            method.instructions.insertBefore(node, new FieldInsnNode(Opcodes.GETFIELD, "com/gildedgames/the_aether/containers/inventory/InventoryAccessories", "player", "Lnet/minecraft/entity/player/EntityPlayer;"));
                            method.instructions.insertBefore(node.getPrevious(), new VarInsnNode(Opcodes.ALOAD, 0));
                            ((MethodInsnNode) node).setOpcode(Opcodes.INVOKESTATIC);
                            ((MethodInsnNode) node).owner = Type.getInternalName(ASMMethods.class);
                            ((MethodInsnNode) node).name = "getAccessoryStack";
                            ((MethodInsnNode) node).desc = "(ILnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";
                        } else if (((MethodInsnNode) node).name.equals("set")) {
                            method.instructions.insertBefore(node, new FieldInsnNode(Opcodes.GETFIELD, "com/gildedgames/the_aether/containers/inventory/InventoryAccessories", "player", "Lnet/minecraft/entity/player/EntityPlayer;"));
                            method.instructions.insertBefore(node.getPrevious(), new VarInsnNode(Opcodes.ALOAD, 0));
                            ((MethodInsnNode) node).setOpcode(Opcodes.INVOKESTATIC);
                            ((MethodInsnNode) node).owner = Type.getInternalName(ASMMethods.class);
                            ((MethodInsnNode) node).name = "setAccessoryStack";
                            ((MethodInsnNode) node).desc = "(ILnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";
                        }
                    }
                }
            }

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }
        else if (transformedName.equals("com.gildedgames.the_aether.client.PlayerGloveRenderer") ||
                transformedName.equals("com.gildedgames.the_aether.client.renders.entities.layer.AccessoriesLayer")) {
            ClassNode classNode = new ClassNode();
            new ClassReader(basicClass).accept(classNode, ClassReader.SKIP_FRAMES);

            for(MethodNode method : classNode.methods) {
				if ("renderArm".equals(method.name) || "renderLeftArmGlove".equals(method.name) || "renderRightGlove".equals(method.name)
                        || "doRenderLayer".equals(method.name))
				{
                    for (AbstractInsnNode node : method.instructions.toArray()) {
                        if (node instanceof LdcInsnNode) {
                            if (((LdcInsnNode) node).cst.toString().equals("Lcom/gildedgames/the_aether/items/accessories/ItemAccessory;")) {
                                ((LdcInsnNode)node).cst = Type.getType(ItemBaubles.class);
                            }
                            else if (((LdcInsnNode) node).cst.toString().equals("Lcom/gildedgames/the_aether/items/accessories/ItemAccessoryDyable;")) {
                                ((LdcInsnNode)node).cst = Type.getType(LeatherGloves.class);
                            }
                        }
                    }
				}
			}

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }

        return basicClass;
    }
}
