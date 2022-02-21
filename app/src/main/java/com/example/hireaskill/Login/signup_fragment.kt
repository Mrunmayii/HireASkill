package com.example.hireaskill.Login
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.hireaskill.R
import com.example.hireaskill.databinding.FragmentLoginFragmentBinding
import com.example.hireaskill.databinding.FragmentSignupFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class signup_fragment : Fragment() {

    private lateinit var fireAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_signup_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireAuth= FirebaseAuth.getInstance()

        var button : Button = view.findViewById(R.id.registerNow)
        var name : EditText = view.findViewById(R.id.txtName)
        var email : EditText = view.findViewById(R.id.txtEmail)
        var pass1 : EditText = view.findViewById(R.id.txtPass)
        var pass2 : EditText = view.findViewById(R.id.txtPassConfirm)

        button.setOnClickListener{
            if(name.text.isEmpty()){
                name.error="Name can not be empty!"
                name.requestFocus()
            }
            else if(email.text.toString().isEmpty()){
                email.error="Email can not be empty!"
                email.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error="Invalid email format!"
                email.requestFocus()

            }
            else if(pass1.text.toString().isEmpty()){
                pass1.error="Password can not be empty!"
                pass1.requestFocus()
            }
            else if(pass1.text.toString().length<6){
                pass1.error="Password length should be at least 6!"
                pass1.requestFocus()
            }
            else if(pass2.text.toString().isEmpty()){
                pass2.error="Confirm password can not be empty!"
                pass2.requestFocus()
            }

            else if(pass2.text.toString()!=pass1.text.toString()){
                pass2.error="Password mismatched!"
                pass2.requestFocus()
            }


            else {
                fireAuth.createUserWithEmailAndPassword(email.text.toString(),pass1.text.toString()).addOnSuccessListener {
                    Toast.makeText(context, "ACCOUNT CREATED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{e->
                    Toast.makeText(context, "ACCOUNT CREATION FAILED DUE TO $e", Toast.LENGTH_SHORT).show()

                }

            }
        }
    }


    }


