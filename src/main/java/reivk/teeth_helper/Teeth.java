package reivk.teeth_helper;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reivk.teeth_helper.block.MotherComputer;
import reivk.teeth_helper.item.MotionSensor;

public class Teeth implements ModInitializer {
	public static final String MOD_ID = "teeth";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Register Blocks Method
	private void registerBlock(String identifier, Block object) {
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, identifier), object);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, identifier), new BlockItem(object, new FabricItemSettings()));
	}

	// Sound Events
	private static Identifier RADAR_BASS_ID = new Identifier(MOD_ID, "radar_bass");
	private static Identifier RADAR_WHIR_ID = new Identifier(MOD_ID, "radar_whir");
	public static SoundEvent RADAR_BASS = SoundEvent.of(RADAR_BASS_ID);
	public static SoundEvent RADAR_WHIR = SoundEvent.of(RADAR_WHIR_ID);

	//	Block Initialization
	public static final Block mother_panel = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block mother_panel_blinking = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block mother_panel_middle_slab = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block mother_panel_top_slab_small_label = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block mother_panel_top_slab_large_label = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block mother_panel_top_slab = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block panel = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final Block double_panel = new Block(FabricBlockSettings.create().strength(4.0f));
	public static final MotherComputer mother_computer = new MotherComputer(FabricBlockSettings.create());

	// Creative Tab
	private static final ItemGroup TEETH_ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(mother_panel))
			.displayName(Text.translatable("itemGroup.teeth"))
			.entries((context, entries) -> {
				entries.add(mother_panel);
				entries.add(mother_panel_blinking);
				entries.add(mother_panel_middle_slab);
				entries.add(mother_panel_top_slab_small_label);
				entries.add(mother_panel_top_slab_large_label);
				entries.add(mother_panel_top_slab);
				entries.add(panel);
				entries.add(double_panel);
				entries.add(mother_computer);
			})
			.build();

	// Mod Initialization
	@Override
	public void onInitialize() {

		//	Creative Tab
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "teeth"), TEETH_ITEM_GROUP);

		//  Sounds
		Registry.register(Registries.SOUND_EVENT, RADAR_BASS_ID, RADAR_BASS);
		Registry.register(Registries.SOUND_EVENT, RADAR_WHIR_ID, RADAR_WHIR);

		//	Items
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "motion_sensor"), new MotionSensor());

		//	Building Blocks (Basic)
		registerBlock("mother_panel", mother_panel);
		registerBlock("mother_panel_blinking", mother_panel_blinking);
		registerBlock("mother_panel_middle_slab", mother_panel_middle_slab);
		registerBlock("mother_panel_top_slab_small_label", mother_panel_top_slab_small_label);
		registerBlock("mother_panel_top_slab_large_label", mother_panel_top_slab_large_label);
		registerBlock("mother_panel_top_slab", mother_panel_top_slab);
		registerBlock("panel", panel);
		registerBlock("double_panel", double_panel);
		registerBlock("mother_computer", mother_computer);


	}

	public static void log(String msg) {
		LOGGER.info(msg);
		}
}