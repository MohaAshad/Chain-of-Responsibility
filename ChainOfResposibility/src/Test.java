public class Test {
    public static void main(String[] args) {
        AuthService step1=new Step1();
        AuthService step2=new Step2();
        AuthService step3=new Step3();

        Request request=new Request("www.login.php","www.login.php?username=abc& password=555");


        step1.nextStep(step2);
        step2.nextStep(step3);
        step3.steps(request);

    }


}

abstract class AuthService{

    protected AuthService steps;

    public void nextStep(AuthService steps){

        this.steps=steps;
    }
    public abstract void steps(Request request);



}
class Request{

    String url;

    String parameter;
    public  Request(String url,String parameter){
        this.url=url;
        this.parameter=parameter;

    }


}

class Step1 extends AuthService{


    @Override
    public void steps(Request request) {
        String text=request.url;
        if(text.contains(".php")){
            System.out.println("Valid URL");

            this.steps.steps(request);
        }else {
            System.out.println("invalid URL");

        }


    }
}
class Step2 extends AuthService{

    @Override
    public void steps(Request request) {
        String text = request.parameter;

        if(text.contains("username")&& text.contains("password")){
            System.out.println("Valid parameters");

            this.steps.steps(request);

        }else{

            System.out.println("text are invalid");
        }


    }
}
class Step3 extends AuthService{

    @Override
    public void steps(Request request) {
        String text=  request.parameter;

        if(text.contains("username=abc")&&text.contains("password=555")){

            System.out.println("Login successfully");

        }else {

            System.out.println("invalid username and password");
        }


    }
}
