<!-- Modal -->
<div class="modal fade" id="modal_server" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modal_title">Website Under Maintance</h5>
      </div>
      <div class="modal-body">
        <span id="message">
         Our website is currently undergoing scheduled maintenance .We'll be here soon with our new awesome site or function subscribe to get notified
        </span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" id="close" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="signin"><#if signin?? && signin?has_content>${signin}
        <#else>Signin
        </#if></button>
      </div>
    </div>
  </div>
</div>