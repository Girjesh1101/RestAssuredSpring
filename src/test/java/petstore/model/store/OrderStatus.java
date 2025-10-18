package petstore.model.store;

    public  enum OrderStatus {
        PLACED("placed"),
        APPROVED("approved"),
        DELIVERED("delivered");

        private  final String value;

        OrderStatus(String value){
            this.value = value;
        }

        public  String getValue(){
            return value;
        }

        public  static  OrderStatus fromString(String value){
            for(OrderStatus status : OrderStatus.values()){
                if(status.getValue().equalsIgnoreCase(value)){
                    return  status;
                }
            }
            throw  new IllegalArgumentException("Unknown order status : "+ value);
        }

}
