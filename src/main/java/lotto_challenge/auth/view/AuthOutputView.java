package lotto_challenge.auth.view;

public class AuthOutputView {

    public void outputExceptionMessage(final IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
