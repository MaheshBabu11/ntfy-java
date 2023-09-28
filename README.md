
# Ntfy Java  <img align="right" src="https://github.com/binwiederhier/ntfy/blob/main/web/public/static/images/ntfy.png">

Java library for publishing/recieving message from a [ntfy](https://github.com/binwiederhier/ntfy) server.

ntfy (pronounce: notify) is a simple HTTP-based pub-sub notification service. It allows you to send notifications to your 
phone or desktop via scripts from any computer, entirely without signup, cost or setup. It’s also open source if you want 
to run your own. Visit ntfy.sh for more details.

ntfy-java is a java wrapper for this service.The workflow is to replicate the GET/POST calls to ntfy based servers and 
provide a neat way to create a client/streaming service to send/receive notifications. 