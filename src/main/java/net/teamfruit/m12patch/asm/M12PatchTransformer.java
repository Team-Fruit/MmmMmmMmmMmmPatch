package net.teamfruit.m12patch.asm;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import net.minecraft.launchwrapper.IClassTransformer;
import net.teamfruit.m12patch.Log;
import net.teamfruit.m12patch.asm.lib.VisitorHelper;
import net.teamfruit.m12patch.asm.lib.VisitorHelper.TransformProvider;

public class M12PatchTransformer implements IClassTransformer {
	@Override
	public @Nullable byte[] transform(final @Nullable String name, final @Nullable String transformedName, final @Nullable byte[] bytes) {
		if (bytes==null||name==null||transformedName==null)
			return bytes;

		if (transformedName.equals("boni.dummy.EntityDummy"))
			return VisitorHelper.apply(bytes, name, new TransformProvider(ClassWriter.COMPUTE_FRAMES) {
				@Override
				public ClassVisitor createVisitor(final String name, final ClassVisitor cv) {
					Log.log.info(String.format("Patching EntityDummy (class: %s)", name));
					return new EntityDummyVisitor(name, cv);
				}
			});

		return bytes;
	}
}