import React, { useState } from "react";
import { Link } from "react-router-dom";

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault(); //prevents page from refreshing
        console.log("Email:", email);
        console.log("Password:", password);

    };

    return (
        <div>
            <Link to="/"> Home Page</Link>
            <Link to="/Admin"> Admin Page</Link>
            <Link to="/Login"> Login Page</Link>
            <Link to="/Profile"> Profile Page</Link>
            <Link to="/Signup"> Signup Page</Link>
            <Link to="/Tierlist"> TierList Page</Link>

            <h1>Login Page</h1>
            <p>Welcome to the Login page!</p>

            {/*handles the submit action when clicking on the button */}
            <form onSubmit={handleSubmit}>
                <label>
                    Email:
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </label>
                <br />
                <label>
                    Password:
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Login</button>
            </form>
        </div>
    );
}