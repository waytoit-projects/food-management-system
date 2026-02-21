<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Food Management System | Login</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background: url('https://images.unsplash.com/photo-1498837167922-ddd27525d352') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-family: 'Segoe UI', sans-serif;
        }

        /* Dark overlay */
        body::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.65);
        }

        /* Glass Card */
        .login-card {
            width: 400px;
            border-radius: 20px;
            position: relative;
            z-index: 1;

            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);

            border: 1px solid rgba(255, 255, 255, 0.3);
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4);
        }
        .btn-dark {
		    border-radius: 12px;
		    background-color: #a7851d;
		    border: none;
		}

        .form-control {
            border-radius: 12px;
        }

        .input-group-text {
            border-radius: 12px 0 0 12px;
        }


        .btn-dark:hover {
            background-color: #111827;
        }

        .text-light-custom {
            color: #f1f5f9;
        }

    </style>
</head>
<body>

<div class="card p-4 login-card">

    <div class="text-center mb-4">
        <div class="bg-light rounded-circle d-inline-flex p-3 shadow-sm">
            <i class="bi bi-egg-fried fs-4"></i>
        </div>

        <h4 class="mt-3 text-light-custom">Food Management System</h4>

        <p class="text-light small">
            Manage food inventory, orders, and operations efficiently.
        </p>
    </div>

    <!-- Login Form -->
    <form action="LoginServlet" method="post">

        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text">
                    <i class="bi bi-envelope"></i>
                </span>
                <input type="email" class="form-control" name="email"
                       placeholder="Enter your email" required>
            </div>
        </div>

        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text">
                    <i class="bi bi-lock"></i>
                </span>
                <input type="password" class="form-control" name="password"
                       placeholder="Enter your password" required>
            </div>
        </div>

        <div class="text-end mb-3">
            <a href="#" class="small text-decoration-none text-light">
                Forgot Password?
            </a>
        </div>

        <div class="d-grid mb-3">
            <button type="submit" class="btn btn-dark">
                Login
            </button>
        </div>

        <div class="text-center text-light small">
            Secure access for authorized staff only
            <br>
            <strong>Powered by WayToIT</strong>
        </div>

    </form>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>