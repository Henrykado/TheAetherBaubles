package henrykado.aetherbaubles.asm;

import henrykado.aetherbaubles.baubles.ItemBaubles;
import henrykado.aetherbaubles.baubles.LeatherGloves;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class ClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("com.gildedgames.the_aether.client.PlayerGloveRenderer") ||
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
