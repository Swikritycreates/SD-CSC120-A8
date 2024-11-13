
/**
 * attributes from contract and extra added to adjust the fucntions of a bird
 */
public class Bird implements Contract{
    public String item;
    public String direction;
    public String name;
    public int height;
    public int distance;
    public int n;
    public int weight;
    public int steps;
    public boolean status; //true = flying, false = resting
    public boolean isWalking;
    public int foodGrams;

    /**
     * constructor of the bird class
     * @param name of the bird
     * @param weight its weight
     */
    public Bird(String name, int weight){
        this.name = name;
        this.item = null;
        this.direction= "";
        this.height= 0; //meters
        this.distance = 0; //meters
        this.weight = weight; // in grams
        this.foodGrams = 0; //grams
        this.status = false;
        this.isWalking = false;
        this.n = 0;
        this.steps = 0;
        }

        /**
         * method to grab something in its claw.
         * since bird cannot carry many items max item is set to 1
         */
    
    public void grab(String item){
        if(this.n < 1){
            this.item = item;
            this.n += 1;
            System.out.println(this.name + " has grabbed " + this.item);
        }else{
            throw new RuntimeException("Birdie has only two claws, can't take no more!");
        }
    
    }

    /**
     * method to drop the item birdie has been carrying in its claw
     */

    public String drop(String item){
         if (!this.item.equals(item)){
            throw new RuntimeException(this.name + " doesn't have "+ item);
        } if (this.n == 0 && this.item == null){
            System.out.println("Birdie is carrying nothing at the moment.");
        }
        this.n -=1;
        String droppedItem = this.item;
        this.item = null;
        System.out.println(this.name+ "has dropped" + this.item);
        return droppedItem;
    }
    
    /**
     * @param item to check if the bird is currently holding that item
     */

    public void examine(String item){
        //checks if bird is carrying an item
        if(this.item != null && this.item.equals(item)){
            System.out.println(this.name+ " is carrying "+ this.item);
        } else{
            System.out.println(this.name+ " doesn't have "+ item);
        }
    }

    /**
     * method to decrease the weight of the bird by a default value of 50
     * @returns current weight
     */

   public Number shrink(){
    this.weight -= 50;
    System.out.println("Current Weight: "+ this.weight +" grams");
    return this.weight;
    }
    

    /**
     * method to use the item it is holding
     * @param item is passed by the user
     */
    public void use(String item){
        if (this.status){
            throw new RuntimeException("Call rest method first to use item.");
        } 
        if (!this.status){
            System.out.println("The bird is using "+ this.item);
        }
    }

    /**
     * @param direction of the side the bird is directed to walk
     * increases as a default value of 10 steps.
     */
    public boolean walk(String direction){
        if (this.status){
            throw new RuntimeException("Birdie is flying. How can it walk on the sky?");
        }
        this.direction = direction;
        this.steps += 10;
        this.isWalking = true;
        System.out.println(this.name+ " is walking towards "+ this.direction);
        return isWalking;
    }

    /**
     * makes the bird fly by taking 
     * @param x for the height
     * @param y for the distance
     */

    public boolean fly(int x, int y){
        if (this.isWalking){
            this.isWalking = false; // to stop walking so this lil b can fly
            System.out.println(this.name + "has stopped walking");
        }
        this.height = x;
        this.distance = y;
        this.status = true;
        System.out.println(this.name + " is flying at a height of "+ this.height + " meters for "+ this. distance + " meters.");
        return this.status;
    }

    /**
     * called by grow method to feed the bird before increasing its weight
     * @param foodGrams is the amount of food feeded to the bird
     */
    public void feed(int foodGrams){
        this.foodGrams+=foodGrams;
        System.out.println(this.name + " just fed "+ foodGrams + " grams. Total Food = "+ this.foodGrams + " grams.");
    }

    /**
     * replicates increase in weight to show the growth of the bird
     * @return its weight at the current moment
     */

    public Number grow(){
        this.feed(50);
        this.weight += 50;
        System.out.println(this.name + " has grown after feeding. Current weight:"+ this.weight);
        return this.weight;
        
    }

    /**
     * changes the flying or walking status to false to presume it as a resting position
     */
    public void rest(){
        if (!this.status && !this.isWalking){
            throw new RuntimeException("Its already resting");
        }
        this.status = false;
        this.isWalking = false;
        System.out.println(this.name + " is resting.");
    }

    /**
     * resets the bird to action opposite to the last action
     */

    public void undo(){
        System.out.println(this.name +" is backing out of last action. We'll reset to previous status.");
        if (this.status) {
            this.status = false;  
        } else if (!this.status){
            this.status = true;
        }
        if (this.isWalking) {
            this.isWalking = false;
        } else if (!this.isWalking){
            this.isWalking = true;
        }
    }

    /**
     * main of the bird class
     * runs all methods
     * @param args
     */

    public static void main(String[] args) {
        Bird dove = new Bird("Duvi", 350);
        System.out.println(dove);
        dove.grab("worms");
        dove.fly(4,7);
        dove.undo();
        dove.walk("East");
        dove.rest();
        dove.grow();
        dove.examine("worms");
        dove.examine("flyers");
        dove.use("worms");
        dove.drop("worms");
        dove.shrink();
        dove.drop("me");
        
    }

}

