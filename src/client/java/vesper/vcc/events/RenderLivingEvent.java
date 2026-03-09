package vesper.vcc.events;


import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.jetbrains.annotations.NotNull;

public interface RenderLivingEvent {
    //? 1.21.1 {
    /*Event<RenderLivingEvent> POST = EventFactory.createArrayBacked(RenderLivingEvent.class, (listeners) -> (renderer, matrices, vertexConsumers, light, tickDelta) ->{
        for (RenderLivingEvent listener : listeners){
            listener.postRender(renderer, matrices, vertexConsumers, light, tickDelta);
        }
    });

    void postRender(LivingEntityRenderer<?, ?> renderer, PoseStack matrices, MultiBufferSource vertexConsumers, int light, float tickDelta) throws IllegalAccessException;
    *///?} 1.21.11 {
    Event<@NotNull RenderLivingEvent> POST = EventFactory.createArrayBacked(RenderLivingEvent.class, (listeners) -> (renderer, matrices) ->{
        for (RenderLivingEvent listener : listeners){
            listener.postRender(renderer, matrices);
        }
    });

    void postRender(LivingEntityRenderer<?, ?, ?> renderer, PoseStack matrices) throws IllegalAccessException;
    //?}
}
