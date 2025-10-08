package vesper.vcc.events;


import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;

public interface RenderLivingEvent {
    Event<RenderLivingEvent> POST = EventFactory.createArrayBacked(RenderLivingEvent.class, (listeners) -> (renderer, matrices, vertexConsumers, light, tickDelta) ->{
        for (RenderLivingEvent listener : listeners){
            listener.postRender(renderer, matrices, vertexConsumers, light, tickDelta);
        }
    });

    void postRender(LivingEntityRenderer<?, ?> renderer, PoseStack matrices, MultiBufferSource vertexConsumers, int light, float tickDelta) throws IllegalAccessException;
}
