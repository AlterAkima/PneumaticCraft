package pneumaticCraft.client.model;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;

import org.lwjgl.opengl.GL11;

import pneumaticCraft.lib.Models;
import pneumaticCraft.lib.Textures;

public class BaseModel implements IBaseModel{

    protected final IModelCustom model;
    protected String[] staticParts, dynamicParts;
    protected final ResourceLocation resLoc;
    public boolean rotatable;

    public BaseModel(String name){
        this(name, null);
    }

    public BaseModel(String name, String[] staticParts){
        this(name, staticParts, null);
    }

    public BaseModel(String name, String[] staticParts, String[] dynamicParts){
        model = AdvancedModelLoader.loadModel(new ResourceLocation(Models.MODELS + name));
        resLoc = new ResourceLocation(Textures.MODEL_LOCATION + name.substring(0, name.lastIndexOf('.')) + ".png");
        this.staticParts = staticParts;
        this.dynamicParts = dynamicParts;
    }

    @Override
    public void renderStatic(float size, TileEntity te){
        GL11.glPushMatrix();
        if(model instanceof WavefrontObject) {
            if(te != null) {
                GL11.glRotated(180, 1, 0, 0);
                GL11.glRotated(90, 0, -1, 0);
                GL11.glTranslated(-8, 0, 8);
            } else {
                GL11.glRotated(180, -1, 0, 0);
                GL11.glTranslated(-8, 0, 8);
            }

        }
        if(staticParts != null) {
            model.renderOnly(staticParts);
        } else {
            model.renderAll();
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderDynamic(float size, TileEntity te, float partialTicks){
        if(dynamicParts != null) {
            GL11.glPushMatrix();
            GL11.glScaled(size, size, size);
            model.renderOnly(dynamicParts);
            GL11.glPopMatrix();
        }
    }

    @Override
    public ResourceLocation getModelTexture(TileEntity tile){
        return resLoc;
    }

    @Override
    public boolean rotateModelBasedOnBlockMeta(){
        return rotatable;
    }
}
