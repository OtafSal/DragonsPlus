package com.sorensmods.dragonsplus.entity.client.enderdragonRendering;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sorensmods.dragonsplus.DragonsPlus;
import com.sorensmods.dragonsplus.entity.ModEnderDragon;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ModEnderDragonModel<T extends ModEnderDragon> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(DragonsPlus.MOD_ID, "modenderdragon"), "main");
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head_neck;
	private final ModelPart neck;
	private final ModelPart neck5;
	private final ModelPart neck4;
	private final ModelPart neck1;
	private final ModelPart neck2;
	private final ModelPart neck3;
	private final ModelPart head;
	private final ModelPart mirrored;
	private final ModelPart jaw;
	private final ModelPart tail;
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
	private final ModelPart left_wing_root;
	private final ModelPart left_wing;
	private final ModelPart left_wing_tip;
	private final ModelPart right_wing_root;
	private final ModelPart right_wing;
	private final ModelPart right_wing_tip;
	private final ModelPart legs_front;
	private final ModelPart front_left_leg_root;
	private final ModelPart front_left_leg;
	private final ModelPart front_left_shin;
	private final ModelPart front_left_foot;
	private final ModelPart front_right_leg_root;
	private final ModelPart front_right_leg;
	private final ModelPart front_right_shin;
	private final ModelPart front_right_foot;
	private final ModelPart legs_back;
	private final ModelPart back_left_leg_root;
	private final ModelPart back_left_leg;
	private final ModelPart back_left_shin;
	private final ModelPart back_left_foot;
	private final ModelPart back_right_leg_root;
	private final ModelPart back_left_leg4;
	private final ModelPart back_left_shin4;
	private final ModelPart back_left_foot4;

	public ModEnderDragonModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head_neck = this.root.getChild("head_neck");
		this.neck = this.head_neck.getChild("neck");
		this.neck5 = this.neck.getChild("neck5");
		this.neck4 = this.neck.getChild("neck4");
		this.neck1 = this.neck.getChild("neck1");
		this.neck2 = this.neck.getChild("neck2");
		this.neck3 = this.neck.getChild("neck3");
		this.head = this.head_neck.getChild("head");
		this.mirrored = this.head.getChild("mirrored");
		this.jaw = this.head.getChild("jaw");
		this.tail = this.root.getChild("tail");
		this.tail1 = this.tail.getChild("tail1");
		this.tail2 = this.tail.getChild("tail2");
		this.tail3 = this.tail.getChild("tail3");
		this.tail4 = this.tail.getChild("tail4");
		this.tail5 = this.tail.getChild("tail5");
		this.tail6 = this.tail.getChild("tail6");
		this.tail7 = this.tail.getChild("tail7");
		this.tail8 = this.tail.getChild("tail8");
		this.tail9 = this.tail.getChild("tail9");
		this.tail10 = this.tail.getChild("tail10");
		this.tail11 = this.tail.getChild("tail11");
		this.tail12 = this.tail.getChild("tail12");
		this.left_wing_root = this.root.getChild("left_wing_root");
		this.left_wing = this.left_wing_root.getChild("left_wing");
		this.left_wing_tip = this.left_wing_root.getChild("left_wing_tip");
		this.right_wing_root = this.root.getChild("right_wing_root");
		this.right_wing = this.right_wing_root.getChild("right_wing");
		this.right_wing_tip = this.right_wing_root.getChild("right_wing_tip");
		this.legs_front = this.root.getChild("legs_front");
		this.front_left_leg_root = this.legs_front.getChild("front_left_leg_root");
		this.front_left_leg = this.front_left_leg_root.getChild("front_left_leg");
		this.front_left_shin = this.front_left_leg_root.getChild("front_left_shin");
		this.front_left_foot = this.front_left_leg_root.getChild("front_left_foot");
		this.front_right_leg_root = this.legs_front.getChild("front_right_leg_root");
		this.front_right_leg = this.front_right_leg_root.getChild("front_right_leg");
		this.front_right_shin = this.front_right_leg_root.getChild("front_right_shin");
		this.front_right_foot = this.front_right_leg_root.getChild("front_right_foot");
		this.legs_back = this.root.getChild("legs_back");
		this.back_left_leg_root = this.legs_back.getChild("back_left_leg_root");
		this.back_left_leg = this.back_left_leg_root.getChild("back_left_leg");
		this.back_left_shin = this.back_left_leg_root.getChild("back_left_shin");
		this.back_left_foot = this.back_left_leg_root.getChild("back_left_foot");
		this.back_right_leg_root = this.legs_back.getChild("back_right_leg_root");
		this.back_left_leg4 = this.back_right_leg_root.getChild("back_left_leg4");
		this.back_left_shin4 = this.back_right_leg_root.getChild("back_left_shin4");
		this.back_left_foot4 = this.back_right_leg_root.getChild("back_left_foot4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, -34.0F, -16.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, 0.0F, -16.0F, 24.0F, 24.0F, 64.0F, new CubeDeformation(0.0F))
		.texOffs(220, 53).addBox(-1.0F, -6.0F, -10.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(220, 53).addBox(-1.0F, -6.0F, 10.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(220, 53).addBox(-1.0F, -6.0F, 30.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_neck = root.addOrReplaceChild("head_neck", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, -60.0F));

		PartDefinition neck = head_neck.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck5 = neck.addOrReplaceChild("neck5", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck4 = neck.addOrReplaceChild("neck4", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition neck1 = neck.addOrReplaceChild("neck1", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 40.0F));

		PartDefinition neck2 = neck.addOrReplaceChild("neck2", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 30.0F));

		PartDefinition neck3 = neck.addOrReplaceChild("neck3", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 20.0F));

		PartDefinition head = head_neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(176, 44).addBox(-6.0F, -1.0F, -24.0F, 12.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(112, 30).addBox(-8.0F, -8.0F, -10.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.0F, -12.0F, -4.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(112, 0).addBox(3.0F, -3.0F, -22.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -10.0F));

		PartDefinition mirrored = head.addOrReplaceChild("mirrored", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -18.0F, -28.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(112, 0).mirror().addBox(-5.0F, -9.0F, -46.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 6.0F, 24.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(176, 65).addBox(-6.0F, 0.0F, -16.0F, 12.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -8.0F));

		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 52.0F));

		PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 10.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail3 = tail.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 20.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail4 = tail.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 30.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail5 = tail.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 40.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail6 = tail.addOrReplaceChild("tail6", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 50.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail7 = tail.addOrReplaceChild("tail7", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 60.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail8 = tail.addOrReplaceChild("tail8", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 70.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail9 = tail.addOrReplaceChild("tail9", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 80.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail10 = tail.addOrReplaceChild("tail10", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 90.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail11 = tail.addOrReplaceChild("tail11", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 100.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition tail12 = tail.addOrReplaceChild("tail12", CubeListBuilder.create().texOffs(192, 104).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-1.0F, -9.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 110.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition left_wing_root = root.addOrReplaceChild("left_wing_root", CubeListBuilder.create(), PartPose.offset(12.0F, 1.0F, -6.0F));

		PartDefinition left_wing = left_wing_root.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(112, 88).mirror().addBox(0.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-56, 88).mirror().addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_wing_tip = left_wing_root.addOrReplaceChild("left_wing_tip", CubeListBuilder.create().texOffs(112, 136).mirror().addBox(0.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(-56, 144).mirror().addBox(0.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(56.0F, 0.0F, 0.0F));

		PartDefinition right_wing_root = root.addOrReplaceChild("right_wing_root", CubeListBuilder.create(), PartPose.offset(-12.0F, 1.0F, -6.0F));

		PartDefinition right_wing = right_wing_root.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(-56, 88).addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F))
		.texOffs(112, 88).addBox(-56.0F, -4.0F, -4.0F, 56.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_wing_tip = right_wing_root.addOrReplaceChild("right_wing_tip", CubeListBuilder.create().texOffs(-56, 144).addBox(-56.0F, 0.0F, 2.0F, 56.0F, 0.0F, 56.0F, new CubeDeformation(0.0F))
		.texOffs(112, 136).addBox(-56.0F, -2.0F, -2.0F, 56.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-56.0F, 0.0F, 0.0F));

		PartDefinition legs_front = root.addOrReplaceChild("legs_front", CubeListBuilder.create(), PartPose.offset(12.0F, 16.0F, -6.0F));

		PartDefinition front_left_leg_root = legs_front.addOrReplaceChild("front_left_leg_root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_left_leg = front_left_leg_root.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(141, 181).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_left_shin = front_left_leg_root.addOrReplaceChild("front_left_shin", CubeListBuilder.create().texOffs(224, 136).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition front_left_foot = front_left_leg_root.addOrReplaceChild("front_left_foot", CubeListBuilder.create().texOffs(145, 106).addBox(-3.0F, -6.0F, -12.0F, 8.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 44.0F, 0.0F));

		PartDefinition front_right_leg_root = legs_front.addOrReplaceChild("front_right_leg_root", CubeListBuilder.create(), PartPose.offset(-24.0F, 0.0F, 0.0F));

		PartDefinition front_right_leg = front_right_leg_root.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(141, 181).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 21.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_right_shin = front_right_leg_root.addOrReplaceChild("front_right_shin", CubeListBuilder.create().texOffs(224, 136).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition front_right_foot = front_right_leg_root.addOrReplaceChild("front_right_foot", CubeListBuilder.create().texOffs(145, 106).addBox(-4.0F, -6.0F, -12.0F, 8.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 44.0F, 0.0F));

		PartDefinition legs_back = root.addOrReplaceChild("legs_back", CubeListBuilder.create(), PartPose.offset(16.0F, 12.0F, 51.0F));

		PartDefinition back_left_leg_root = legs_back.addOrReplaceChild("back_left_leg_root", CubeListBuilder.create(), PartPose.offset(-5.0F, -6.0F, 0.0F));

		PartDefinition back_left_leg = back_left_leg_root.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(185, 178).addBox(-3.0F, 11.0F, -16.0F, 9.0F, 21.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(125, 145).addBox(-3.0F, 28.0F, -16.0F, 9.0F, 10.0F, 26.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -5.0F, -6.0F));

		PartDefinition back_left_shin = back_left_leg_root.addOrReplaceChild("back_left_shin", CubeListBuilder.create().texOffs(204, 4).addBox(-2.0F, -2.0F, 2.0F, 7.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, -6.0F));

		PartDefinition back_left_foot = back_left_leg_root.addOrReplaceChild("back_left_foot", CubeListBuilder.create().texOffs(127, 6).addBox(-3.0F, -12.0F, -15.0F, 9.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 60.0F, 2.0F));

		PartDefinition back_right_leg_root = legs_back.addOrReplaceChild("back_right_leg_root", CubeListBuilder.create(), PartPose.offset(-30.0F, -6.0F, 0.0F));

		PartDefinition back_left_leg4 = back_right_leg_root.addOrReplaceChild("back_left_leg4", CubeListBuilder.create().texOffs(185, 178).addBox(-3.0F, 11.0F, -16.0F, 9.0F, 21.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(125, 145).addBox(-3.0F, 28.0F, -16.0F, 9.0F, 10.0F, 26.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -5.0F, -6.0F));

		PartDefinition back_left_shin4 = back_right_leg_root.addOrReplaceChild("back_left_shin4", CubeListBuilder.create().texOffs(204, 4).addBox(-2.0F, -2.0F, 2.0F, 7.0F, 21.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 30.0F, -6.0F));

		PartDefinition back_left_foot4 = back_right_leg_root.addOrReplaceChild("back_left_foot4", CubeListBuilder.create().texOffs(127, 6).addBox(-3.0F, -12.0F, -15.0F, 9.0F, 4.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 60.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim( ModEnderDragon entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer( PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int pColor) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, pColor);
	}

	@Override
	public ModelPart root(){

		return root;
	}
}