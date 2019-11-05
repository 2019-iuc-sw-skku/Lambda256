package kr.co.softcampus.login;

/*
 * Class name: State
 * Descriptions: This class is about all the states that shows which values on the backend side by client has been changed,
 *              for cache coherence check
 * Created at 2019. 11. 06.
 * Made by Keonyoung Shim
 * Contact: +8210-9919-9004
 * Email: kyshim9004@gmail.com
 */

public class State {
    public static boolean isPayment = false;
    public static boolean isTransfer = false;
    public static boolean isGetToken = false;
    public static boolean isNameChanged = false;
}
