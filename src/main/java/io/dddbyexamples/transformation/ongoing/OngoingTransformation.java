package io.dddbyexamples.transformation.ongoing;

import io.dddbyexamples.transformation.TransformationEvents;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class OngoingTransformation<ASSET> {

    private final Map<String, InstitutionAssets> assets;
    private final String assetKind;

    private final AssetDetails<ASSET> details;
    private final AssetTransformationValidation<ASSET> validator;

    private final TransformationEvents events;

    public void move(List<MoveAsset> moveAsset) {
        // enrich command and pass to asset specific validator to filter possible transformations
        // moveAsset.stream().filter();

        // general rules:
        // we can move asset only into target institutions
        // cannot move and close same asset

        // update currently known assets and emit KnownAssetsChanged if necessary

        events.emit(AssetsDistributed.moved(assetKind, moveAsset));
    }

    public void close(List<CloseAsset> closeAsset) {
        // enrich command and pass to asset specific validator to filter possible transformations
        // moveAsset.stream().filter();

        // general rules:
        // we can close only assets from transformed institutions
        // cannot move and close same asset

        // update currently known assets and emit KnownAssetsChanged if necessary

        events.emit(AssetsDistributed.close(assetKind, closeAsset));
    }

    static class InstitutionAssets {

        Optional<KnownAssetsChanged> currentlyKnown(Set<String> known) {
            // TODO implement that
            return Optional.empty();
        }
    }
}
