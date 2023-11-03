package descriptions;

import java.util.Set;

public interface WitsDescriptor {
    String getPackageNumber();
    String getItem();
    Set<String> getItemSet();
    String getMnemonic();
}
