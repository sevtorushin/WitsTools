package validators;

import java.util.ArrayList;
import java.util.List;

public class ValidatorBuilder {
    private List<PackageValidator> validators;

    public ValidatorBuilder(List<PackageValidator> validators) {
        this.validators = validators;
    }

    public ValidatorBuilder() {
        this.validators = new ArrayList<>();
    }

    public void add(PackageValidator validator){
        validators.add(validator);
    }

    public boolean isValid(String witsPackage) {
        boolean result = true;
        for(Validator v : validators)
            result = result && v.isValid(witsPackage);
        return result;
    }

    public List<PackageValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<PackageValidator> validators) {
        this.validators = validators;
    }
}
