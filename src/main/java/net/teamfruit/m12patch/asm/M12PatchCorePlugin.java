package net.teamfruit.m12patch.asm;

import java.util.Map;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class M12PatchCorePlugin implements IFMLLoadingPlugin {
	@Override
	public @Nullable String[] getASMTransformerClass() {
		return new String[] {
				M12PatchTransformer.class.getName()
		};
	}

	@Override
	public @Nullable String getModContainerClass() {
		return null;
	}

	@Override
	public @Nullable String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(final @Nullable Map<String, Object> data) {
	}

	@Override
	public @Nullable String getAccessTransformerClass() {
		return null;
	}
}