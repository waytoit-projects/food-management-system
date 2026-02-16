<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login Page</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(to bottom, #cfe9f7, #eaf6fc);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-card {
            width: 400px;
            border-radius: 20px;
            backdrop-filter: blur(10px);
        }

        .form-control {
            border-radius: 12px;
        }

        .btn-dark {
            border-radius: 12px;
        }

        .social-btn {
            border-radius: 12px;
            width: 80px;
        }
    </style>
</head>
<body>

<div class="card shadow-lg p-4 login-card bg-white">

    <div class="text-center mb-4">
        <div class="bg-light rounded-circle d-inline-flex p-3 shadow-sm">
            <i class="bi bi-box-arrow-in-right fs-4"></i>
        </div>
        <h4 class="mt-3">Sign in with email</h4>
        <p class="text-muted small">
            Make a new doc to bring your words, data,
            and teams together. For free
        </p>
    </div>

    <!-- Login Form -->
    <form action="LoginServlet" method="post">

        <div class="mb-3">
            <div class="input-group">
                <span class="input-group-text">
                    <i class="bi bi-envelope"></i>
                </span>
                <input type="email" class="form-control" name="email" placeholder="Email" required>
            </div>
        </div>

        <div class="mb-2">
            <div class="input-group">
                <span class="input-group-text">
                    <i class="bi bi-lock"></i>
                </span>
                <input type="password" class="form-control" name="password" placeholder="Password" required>
            </div>
        </div>

        <div class="text-end mb-3">
            <a href="#" class="small text-decoration-none">Forgot password?</a>
        </div>

        <div class="d-grid mb-3">
            <button type="submit" class="btn btn-dark">
                Get Started
            </button>
        </div>

        <div class="text-center text-muted small mb-3">
            Or sign in with
        </div>

        <div class="d-flex justify-content-center gap-3">
            <button type="button" class="btn btn-light shadow-sm social-btn">
                <i class="bi bi-google"></i>
            </button>
            <button type="button" class="btn btn-light shadow-sm social-btn">
                <i class="bi bi-facebook"></i>
            </button>
            <button type="button" class="btn btn-light shadow-sm social-btn">
                <i class="bi bi-apple"></i>
            </button>
        </div>

    </form>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
