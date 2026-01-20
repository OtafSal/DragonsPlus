package com.sorensmods.dragonsplus.entity.client.enderdragonRendering;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.custom.ModEnderDragon;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ModEnderDragonModel<T extends ModEnderDragon> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DragonsPlus.MOD_ID, "modenderdragon"), "main");
	private final ModelPart body;
	private final ModelPart left_wing;
	private final ModelPart left_wing_leather;
	private final ModelPart left_wing_tip;
	private final ModelPart left_wing_tip_leather;
	private final ModelPart right_wing;
	private final ModelPart right_wing_leather;
	private final ModelPart right_wing_tip;
	private final ModelPart right_wing_tip_leather;
	private final ModelPart legs_front;
	private final ModelPart front_right_leg;
	private final ModelPart front_right_shin;
	private final ModelPart front_right_foot;
	private final ModelPart front_left_leg;
	private final ModelPart front_left_shin;
	private final ModelPart front_left_foot;
	private final ModelPart legs_back;
	private final ModelPart back_left_leg;
	private final ModelPart back_left_knee;
	private final ModelPart back_left_shin;
	private final ModelPart back_left_foot;
	private final ModelPart back_right_leg;
	private final ModelPart back_right_knee;
	private final ModelPart back_right_shin;
	private final ModelPart back_right_foot;
	private final ModelPart neck1;
	private final ModelPart neck2;
	private final ModelPart neck3;
	private final ModelPart neck4;
	private final ModelPart neck5;
	private final ModelPart head;
	private final ModelPart mirrored;
	private final ModelPart jaw;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart tail6;
	private final ModelPart tail7;
	private final ModelPart tail8;
	private final ModelPart tail9;
	private final ModelPart tail10;
	private final ModelPart tail11;
	private final ModelPart tail12;

	public ModEnderDragonModel(ModelPart root) {

		this.body = root.getChild("body");
		this.left_wing = this.body.getChild("left_wing");
		this.left_wing_leather = this.left_wing.getChild("left_wing_leather");
		this.left_wing_tip = this.left_wing.getChild("left_wing_tip");
		this.left_wing_tip_leather = this.left_wing_tip.getChild("left_wing_tip_leather");
		this.right_wing = this.body.getChild("right_wing");
		this.right_wing_leather = this.right_wing.getChild("right_wing_leather");
		this.right_wing_tip = this.right_wing.getChild("right_wing_tip");
		this.right_wing_tip_leather = this.right_wing_tip.getChild("right_wing_tip_leather");
		this.legs_front = this.body.getChild("legs_front");
		this.front_right_leg = this.legs_front.getChild("front_right_leg");
		this.front_right_shin = this.front_right_leg.getChild("front_right_shin");
		this.front_right_foot = this.front_right_shin.getChild("front_right_foot");
		this.front_left_leg = this.legs_front.getChild("front_left_leg");
		this.front_left_shin = this.front_left_leg.getChild("front_left_shin");
		this.front_left_foot = this.front_left_shin.getChild("front_left_foot");
		this.legs_back = this.body.getChild("legs_back");
		this.back_left_leg = this.legs_back.getChild("back_left_leg");
		this.back_left_knee = this.back_left_leg.getChild("back_left_knee");
		this.back_left_shin = this.back_left_knee.getChild("back_left_shin");
		this.back_left_foot = this.back_left_shin.getChild("back_left_foot");
		this.back_right_leg = this.legs_back.getChild("back_right_leg");
		this.back_right_knee = this.back_right_leg.getChild("back_right_knee");
		this.back_right_shin = this.back_right_knee.getChild("back_right_shin");
		this.back_right_foot = this.back_right_shin.getChild("back_right_foot");
		this.neck1 = this.body.getChild("neck1");
		this.neck2 = this.neck1.getChild("neck2");
		this.neck3 = this.neck2.getChild("neck3");
		this.neck4 = this.neck3.getChild("neck4");
		this.neck5 = this.neck4.getChild("neck5");
		this.head = this.neck5.getChild("head");
		this.mirrored = this.head.getChild("mirrored");
		this.jaw = this.head.getChild("jaw");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.tail5 = this.tail4.getChild("tail5");
		this.tail6 = this.tail5.getChild("tail6");
		this.tail7 = this.tail6.getChild("tail7");
		this.tail8 = this.tail7.getChild("tail8");
		this.tail9 = this.tail8.getChild("tail9");
		this.tail10 = this.tail9.getChild("tail10");
		this.tail11 = this.tail10.getChild("tail11");
		this.tail12 = this.tail11.getChild("tail12");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -7.0F, -32.0F, 24.0F, 24.0F, 64.0F, new CubeDeformation(0.0F))
				.texOffs(220, 53).addBox(-1.0F, -13.0F, -26.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(220, 53).addBox(-1.0F, -13.0F, -6.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(220, 53).addBox(-1.0F, -13.0F, 14.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, 20.0F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(112, 88).mirror().addBox(0.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(12.0F, -6.0F, -22.0F));

		PartDefinition left_wing_leather = left_wing.addOrReplaceChild("left_wing_leather", CubeListBuilder.create().texOffs(-56, 88).mirror().addBox(12.0F, -77.0F, -20.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-12.0F, 77.0F, 22.0F));

		PartDefinition left_wing_tip = left_wing.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(112, 136).mirror().addBox(0.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, 0.0F, 0.0F));

		PartDefinition left_wing_tip_leather = left_wing_tip.addOrReplaceChild("left_wing_tip_leather", CubeListBuilder.create().texOffs(-56, 144).mirror().addBox(68.0F, -77.0F, -20.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-68.0F, 77.0F, 22.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(112, 88).addBox(-56.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -6.0F, -22.0F));

		PartDefinition right_wing_leather = right_wing.addOrReplaceChild("right_wing_leather", CubeListBuilder.create().texOffs(-56, 88).addBox(-68.0F, -77.0F, -20.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)), PartPose.offset(12.0F, 77.0F, 22.0F));

		PartDefinition right_wing_tip = right_wing.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(112, 136).addBox(-56.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, 0.0F, 0.0F));

		PartDefinition right_wing_tip_leather = right_wing_tip.addOrReplaceChild("right_wing_tip_leather", CubeListBuilder.create().texOffs(-56, 144).addBox(-124.0F, -77.0F, -20.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)), PartPose.offset(68.0F, 77.0F, 22.0F));

		PartDefinition legs_front = body.addOrReplaceChild("legs_front", CubeListBuilder.create(), PartPose.offset(12.0F, 9.0F, -22.0F));

		PartDefinition front_right_leg = legs_front.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(141, 181).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-24.0F, 0.0F, 1.0F));

		PartDefinition front_right_shin = front_right_leg.addOrReplaceChild("front_right_shin", CubeListBuilder.create().texOffs(224, 136).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition front_right_foot = front_right_shin.addOrReplaceChild("front_right_foot", CubeListBuilder.create().texOffs(145, 106).addBox(-4.0F, 0.0F, -13.0F, 8.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition front_left_leg = legs_front.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(141, 181).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition front_left_shin = front_left_leg.addOrReplaceChild("front_left_shin", CubeListBuilder.create().texOffs(224, 136).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition front_left_foot = front_left_shin.addOrReplaceChild("front_left_foot", CubeListBuilder.create().texOffs(145, 106).addBox(-4.0F, 0.0F, -13.0F, 8.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition legs_back = body.addOrReplaceChild("legs_back", CubeListBuilder.create(), PartPose.offset(16.0F, 5.0F, 35.0F));

		PartDefinition back_left_leg = legs_back.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(185, 178).addBox(-4.0F, -5.0F, -7.0F, 9.0F, 21.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 5.0F, -15.0F));

		PartDefinition back_left_knee = back_left_leg.addOrReplaceChild("back_left_knee", CubeListBuilder.create().texOffs(125, 145).addBox(-3.0F, -4.0F, -7.0F, 9.0F, 10.0F, 26.0F, new CubeDeformation(0.02F)), PartPose.offset(-1.0F, 16.0F, 0.0F));

		PartDefinition back_left_shin = back_left_knee.addOrReplaceChild("back_left_shin", CubeListBuilder.create().texOffs(204, 4).addBox(-2.0F, 1.0F, -4.0F, 7.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 15.0F));

		PartDefinition back_left_foot = back_left_shin.addOrReplaceChild("back_left_foot", CubeListBuilder.create().texOffs(127, 6).addBox(-3.0F, 0.0F, -13.0F, 9.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition back_right_leg = legs_back.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(185, 178).addBox(-5.0F, -5.0F, -7.0F, 9.0F, 21.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(-28.0F, 5.0F, -15.0F));

		PartDefinition back_right_knee = back_right_leg.addOrReplaceChild("back_right_knee", CubeListBuilder.create().texOffs(125, 145).addBox(-5.0F, -4.0F, -7.0F, 9.0F, 10.0F, 26.0F, new CubeDeformation(0.02F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition back_right_shin = back_right_knee.addOrReplaceChild("back_right_shin", CubeListBuilder.create().texOffs(204, 4).addBox(-4.0F, 1.0F, -4.0F, 7.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 15.0F));

		PartDefinition back_right_foot = back_right_shin.addOrReplaceChild("back_right_foot", CubeListBuilder.create().texOffs(127, 6).addBox(-4.0F, 0.0F, -13.0F, 9.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 21.0F, 0.0F));

		PartDefinition neck1 = body.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, -7.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -32.0F));

		PartDefinition neck2 = neck1.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, -8.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));

		PartDefinition neck3 = neck2.addOrReplaceChild("neck3", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, -8.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

		PartDefinition neck4 = neck3.addOrReplaceChild("neck4", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, -8.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

		PartDefinition neck5 = neck4.addOrReplaceChild("neck5", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, -8.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

		PartDefinition head = neck5.addOrReplaceChild("head", CubeListBuilder.create().texOffs(176, 44).addBox(-6.0F, -1.0F, -30.0F, 12.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(112, 30).addBox(-8.0F, -8.0F, -16.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, -12.0F, -10.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(112, 0).addBox(3.0F, -3.0F, -28.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -9.0F));

		PartDefinition mirrored = head.addOrReplaceChild("mirrored", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -18.0F, -28.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(112, 0).mirror().addBox(-5.0F, -9.0F, -46.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.0F, 18.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(176, 65).addBox(-6.0F, 0.0F, -16.0F, 12.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -14.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -1.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 1.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 32.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail5 = tail4.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail6 = tail5.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail7 = tail6.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail8 = tail7.addOrReplaceChild("tail8", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail9 = tail8.addOrReplaceChild("tail9", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail10 = tail9.addOrReplaceChild("tail10", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail11 = tail10.addOrReplaceChild("tail11", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail12 = tail11.addOrReplaceChild("tail12", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, 0.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-1.0F, -9.0F, 2.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(ModEnderDragon entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		 this.root().getAllParts().forEach(ModelPart::resetPose);

		//Set the modelParts for the spine
		entity.anims.UpperSpine.add(head);
		entity.anims.UpperSpine.add(neck1);
		entity.anims.UpperSpine.add(neck2);
		entity.anims.UpperSpine.add(neck3);
		entity.anims.UpperSpine.add(neck4);
		entity.anims.UpperSpine.add(neck5);

		entity.anims.Trunk = root();

		entity.anims.LowerSpine.add(0, tail1);
		entity.anims.LowerSpine.add(1, tail2);
		entity.anims.LowerSpine.add(2, tail3);
		entity.anims.LowerSpine.add(3, tail4);
		entity.anims.LowerSpine.add(4, tail5);
		entity.anims.LowerSpine.add(5, tail6);
		entity.anims.LowerSpine.add(6, tail7);
		entity.anims.LowerSpine.add(7, tail8);
		entity.anims.LowerSpine.add(8, tail9);
		entity.anims.LowerSpine.add(9, tail10);
		entity.anims.LowerSpine.add(10, tail11);
		entity.anims.LowerSpine.add(11, tail12);


		//Only animates when in ground
		if (!entity.base.isFlying()) {
			this.animateWalk(ModEnderDragonAnimations.walking, limbSwing, limbSwingAmount, 4, 2.5f);
		}

		entity.anims.bodyXRot(-60, entity.base.angleX, entity.base.flying , entity.base.movingFly);
		headPitch -= entity.anims.angleStoppedMod;


		 this.animate(entity.anims.IDLE, ModEnderDragonAnimations.Idle, ageInTicks);

		 this.animate(entity.anims.FLYING, ModEnderDragonAnimations.flying, ageInTicks);
		 this.animate(entity.anims.LIFTOFF, ModEnderDragonAnimations.liftoff, ageInTicks);
		 this.animate(entity.anims.LANDING, ModEnderDragonAnimations.landing, ageInTicks);

		 this.animate(entity.anims.SITTING, ModEnderDragonAnimations.Sitting, ageInTicks);
		 this.animate(entity.anims.STANDING, ModEnderDragonAnimations.Standing, ageInTicks);


		 entity.anims.applyHeadRotation(netHeadYaw, headPitch, 6);
		 entity.anims.applyTailRotation(-netHeadYaw*2,entity.base.flying ? -headPitch*2 : 0, 12);


	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int pColor) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, pColor);
	}

	@NotNull
	@Override
	public ModelPart root(){

		return body;
	}
}