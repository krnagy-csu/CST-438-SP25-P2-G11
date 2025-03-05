import React, { useState } from "react";
import { Link } from "react-router-dom";

export default function Signup() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleSignup = (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            setMessage("Passwords do not match!");
            console.log("Error: Passwords do not match.");
            return;
        }

        const newUser = { username, password };
        console.log("Signing up with:", newUser);
    };

    return (
        <div>
            <Link to="/"> Home Page</Link>
            <Link to="/Admin"> Admin Page</Link>
            <Link to="/Login"> Login Page</Link>
            <Link to="/Profile"> Profile Page</Link>
            <Link to="/Signup"> Signup Page</Link>
            <Link to="/Tierlist"> TierList Page</Link>

            <h1>Signup Page</h1>
            <p>Welcome to the Signup page!</p>

            {console.log("Current signup state:", { username, password, confirmPassword })}

            <form onSubmit={handleSignup}>
                <label>
                    Username:
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
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
                <label>
                    Confirm Password:
                    <input
                        type="password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Sign Up</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
}
