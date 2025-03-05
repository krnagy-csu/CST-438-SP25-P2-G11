import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Admin() {
    const [users, setUsers] = useState([]);

    //fetching users from db
    useEffect(() => {
        fetch("http://localhost:8080/user/all") 
            .then((response) => {
                console.log("Response received:", response);
                return response.json();
            })
            .then((data) => {
                console.log("Fetched users:", data); // log fetched data
                setUsers(data);
            })
            .catch((error) => console.error("Error fetching users:", error));
    }, []);
    

    const handleEdit = (id, field, value) => {
        setUsers(users.map(user =>
            user.id === id ? { ...user, [field]: value } : user
        ));
    };

    const handleSave = (user) => {
        console.log("Updated user:", user);
        //will eventually use this to send updated user info to db
    };

    return (
        <div>
            <Link to="/"> Home Page</Link>
            <Link to="/Admin"> Admin Page</Link>
            <Link to="/Login"> Login Page</Link>
            <Link to="/Profile"> Profile Page</Link>
            <Link to="/Signup"> Signup Page</Link>
            <Link to="/Tierlist"> TierList Page</Link>

            <h1>Admin Page</h1>
            <p>Manage users below:</p>

            {users.length === 0 ? <p>Loading users...</p> : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user) => (
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>
                                    <input
                                        type="text"
                                        value={user.username}
                                        onChange={(e) => handleEdit(user.id, "username", e.target.value)}
                                    />
                                </td>
                                <td>
                                    <input
                                        type="password"
                                        value={user.password}
                                        onChange={(e) => handleEdit(user.id, "password", e.target.value)}
                                    />
                                </td>
                                <td>
                                    <button onClick={() => handleSave(user)}>Save</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}
