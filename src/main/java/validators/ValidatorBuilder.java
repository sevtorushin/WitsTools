package validators;

import java.util.ArrayList;
import java.util.List;

public class ValidatorBuilder<T extends Validator> implements Validator{
    private List<T> validators;

    public ValidatorBuilder(List<T> validators) {
        this.validators = validators;
    }

    public ValidatorBuilder() {
        this.validators = new ArrayList<>();
    }

    public void add(T validator){
        validators.add(validator);
    }

    @Override
    public boolean isValid(String witsPackage) {
        boolean result = true;
        for(Validator v : validators)
            result = result && v.isValid(witsPackage);
        return result;
    }

    public List<T> getValidators() {
        return validators;
    }

    public void setValidators(List<T> validators) {
        this.validators = validators;
    }
}
