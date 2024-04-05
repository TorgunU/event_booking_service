INSERT INTO users (name, age, password_hash, role)
VALUES
    ('ADMIN', 30, '$2a$12$yvLV5fWDz7YqoVoXV2X80ODJnUqiNgVzCBpO2XjX1sW8W7S.zITju', 'ROLE_ADMIN'),
    ('Ivan Ivanov', 25, '$2a$12$ds6aVDrdNuLr7f5JHG1l6eIAj1UxNzCxFzGfGHgkGWjBpyhe45jpG', 'ROLE_USER')
ON CONFLICT (name) DO NOTHING;