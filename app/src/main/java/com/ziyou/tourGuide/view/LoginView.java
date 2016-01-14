package com.ziyou.tourGuide.view;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ziyou.tourGuide.R;
import com.ziyou.tourGuide.view.base.TitleBarContentView;
import com.ziyou.tourGuide.view.interfaze.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Edward on 16/1/8.
 */
public class LoginView extends TitleBarContentView implements ILoginView {

    @Bind(R.id.username)
    View username;
    @Bind(R.id.password)
    View password;
    @Bind(R.id.login)
    Button login;

    public LoginView(Context context) {
        super(context);
    }

    @Override
    public View setContentView() {
        View view = View.inflate(getContext(), R.layout.fragment_login,null);
        ButterKnife.bind(this,view);

        EditText inputUsername = (EditText) username.findViewById(R.id.item_input);
        inputUsername.setInputType(InputType.TYPE_CLASS_PHONE);
        inputUsername.setHint(R.string.please_input_phone);

        EditText inputPassword = (EditText) password.findViewById(R.id.item_input);
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //隐藏密码
//        inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);   //显示密码
        inputPassword.setHint(R.string.please_input_password);
        return view;
    }

    @Override
    public EditText getUserNameEdittext() {
        return (EditText) username.findViewById(R.id.item_input);
    }

    @Override
    public EditText getPasswordEdittext() {
        return (EditText) password.findViewById(R.id.item_input);
    }

    @Override
    public Button getLoginButton() {
        return login;
    }
}
