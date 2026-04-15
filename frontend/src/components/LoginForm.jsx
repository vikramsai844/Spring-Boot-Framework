import { useState } from "react";
import axios from 'axios';

function LoginForm() {
  const [user, setUser] = useState({});


    const [logged,setLogged]=useState(false);



    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser(
            {
                ...user,
                [name]: value
            }
        );
    }

    const handleLogin = async () => {
        try {
            const res = await axios.post("http://localhost:8080/signup/login", user);
            alert("login successfull");
            setLogged(true)
        }
        catch (err) {
            alert("login failed");
        }

    }
    const handleRegister = async () => {
        try {
            const res = await axios.post("http://localhost:8080/signup/register", user);
            alert("register successfull");
            setLogged(true)
        }
        catch (err) {
            alert("register failed");
        }

    }
  
    return (
        <>
            <div>
                <label htmlFor="">Email</label>
                <input type="text" name="email" id="email" onChange={handleChange} />

                <br /><br />
                <label htmlFor="">Password</label>
                <input type="text" name="password" id="password" onChange={handleChange} />

                <br /><br />
                <button onClick={handleLogin}>Login</button>
                     <button onClick={handleRegister}>Register</button>
            </div>


          <div>
            {logged && 
            <div>
                <h1>You entered Data:</h1>
            <p>{user.email}</p>
            <p>{user.password}</p></div>}
          </div>

        </>
    )

}

export default LoginForm;