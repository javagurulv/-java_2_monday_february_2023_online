package java2.eln.core.domain;

import javax.persistence.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

// TODO: table ReactionSideProducts is absent
//    @OneToMany
//    @JoinTable(
//            name = "ReactionSideProducts",
//            joinColumns = @JoinColumn(name = "reaction_id"),
//            inverseJoinColumns = @JoinColumn(name = "structure_id")
//    )
//    private List<StructureData> products;

@Entity
@Table(name = "ReactionData")
public class ReactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reactionYield")
    private double reactionYield;

    @OneToOne
    @JoinColumn(name = "structure_mainProduct_id")
    private StructureData mainProduct;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(
            name = "ReactionStartingMaterial",
            joinColumns = @JoinColumn(name = "reaction_id"),
            inverseJoinColumns = @JoinColumn(name = "structure_id")
    )
    private List<StructureData> startingMaterials;


    @OneToOne(mappedBy = "reaction", cascade = CascadeType.ALL)
    private ConditionData conditions;

    private List<StructureData> products;

    public ReactionData() {
    }

    public ReactionData(String code, String name) {
        this.code = code;
        this.name = name;
        this.startingMaterials = new ArrayList<>();
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void addStartingMaterial (StructureData material){
        startingMaterials.add(material);
    }

    public void addProduct (StructureData material){
        products.add(material);
    }

    public void setMainProduct(StructureData mainProduct) {
        this.mainProduct = mainProduct;
    }

    public List<StructureData> getStartingMaterials() {
        return startingMaterials;
    }

    public void setConditions(ConditionData conditions) {
        this.conditions = conditions;
    }
    public ConditionData getConditions() {
        return conditions;
    }

    public List<StructureData> getProducts() {
        return products;
    }

    public StructureData getMainProduct() {
        return mainProduct;
    }

    public void calculateReactionYield(){
        double sm1Mole = (startingMaterials.get(0).getMass()/startingMaterials.get(0).getMW());
        double mpMole = (mainProduct.getMass()/mainProduct.getMW());
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        this.reactionYield = Double.parseDouble(df.format(mpMole/sm1Mole));
    }

    public double getReactionYield() {
        return reactionYield;
    }

    @Override
    public String toString() {
        //calculateReactionYield();
        return "*** baseClasses.ReactionData{" +
                "\n code='" + code + '\'' +
                "\n name='" + name + '\'' +
                "\n startingMaterials=" + startingMaterials +
                "\n products=" + products +
                "\n mainProduct=" + mainProduct +
                "\n Conditions: \n" + conditions +
                "\n reaction yield: " + String.format("%.2f", reactionYield*100) + "%" +
                "} ***";
    }

    public void setStartingMaterials(List<StructureData> startingMaterials) {
        this.startingMaterials = startingMaterials;
    }
}

