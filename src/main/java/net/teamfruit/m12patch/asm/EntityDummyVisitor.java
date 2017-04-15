package net.teamfruit.m12patch.asm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import net.teamfruit.m12patch.asm.lib.DescHelper;
import net.teamfruit.m12patch.asm.lib.MethodMatcher;
import net.teamfruit.m12patch.asm.lib.RefName;

public class EntityDummyVisitor extends ClassVisitor {
	private static class IsEntityAliveMethodVisitor extends MethodVisitor {
		public IsEntityAliveMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  iconst_1
			 * 1  ireturn
			 */
			super.visitInsn(Opcodes.ICONST_1);
			super.visitInsn(Opcodes.IRETURN);
		}
	}

	private final @Nonnull MethodMatcher isEntityAliveMatcher;

	public EntityDummyVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.isEntityAliveMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(boolean.class, new Object[0]), RefName.deobName("isEntityAlive", "func_70089_S"));
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		else if (this.isEntityAliveMatcher.match(name, desc))
			return new IsEntityAliveMethodVisitor(parent);
		else
			return parent;
	}
}