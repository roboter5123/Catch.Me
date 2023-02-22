# production environment
FROM nginx:stable-alpine
ADD /public/ /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]