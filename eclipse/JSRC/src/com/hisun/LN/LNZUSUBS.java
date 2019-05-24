package com.hisun.LN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCQCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class LNZUSUBS {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "LNM31";
    String CPN_LN_RSC_LNTCONT = "LN-RSC-LNTCONT";
    String K_HIS_REMARKS = "MAINTAIN SUBS";
    String K_HIS_RMKS = "SUBS MAINTAIN";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    LNZUSUBS_WS_OUTPUT_LIST WS_OUTPUT_LIST = new LNZUSUBS_WS_OUTPUT_LIST();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    LNCM31 LNCM31 = new LNCM31();
    LNCRSUBS LNCRSUBS = new LNCRSUBS();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNRSUBS LNRHSETO = new LNRSUBS();
    LNRSUBS LNRHSETN = new LNRSUBS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    LNCUSUBS LNCUSUBS;
    public void MP(SCCGWA SCCGWA, LNCUSUBS LNCUSUBS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCUSUBS = LNCUSUBS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZUSUBS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        IBS.init(SCCGWA, LNRHSETN);
        IBS.init(SCCGWA, LNRHSETO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_FUNC_DATA();
        if (pgmRtn) return;
    }
    public void B000_FUNC_DATA() throws IOException,SQLException,Exception {
        if (LNCUSUBS.FUNC == 'A') {
            B000_CHECK_INPUT();
            if (pgmRtn) return;
            B010_CREATE_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_TO_LNRHSETN();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (LNCUSUBS.FUNC == 'U') {
            B000_CHECK_INPUT();
            if (pgmRtn) return;
            B020_MODIFY_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_TO_LNRHSETN();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (LNCUSUBS.FUNC == 'D') {
            B030_DELETE_PROCESS();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (LNCUSUBS.FUNC == 'I') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = LNCMSG_ERROR_MSG.LN_ERR_FUNC_CODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B000_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B010_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCRSUBS);
        IBS.init(SCCGWA, LNRSUBS);
        LNCRSUBS.FUNC = 'A';
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUSUBS.DB_CONTENT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRSUBS);
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        IBS.init(SCCGWA, LNCRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNCUSUBS.DB_CONTENT.PROJ_NO;
        LNCRSUBS.FUNC = 'R';
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUSUBS.DB_CONTENT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNRSUBS);
        LNCRSUBS.FUNC = 'U';
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
    }
    public void B030_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        IBS.init(SCCGWA, LNCRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNCUSUBS.DB_CONTENT.PROJ_NO;
        LNCRSUBS.FUNC = 'R';
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_LNRHSETO();
        if (pgmRtn) return;
        LNCRSUBS.FUNC = 'D';
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        IBS.init(SCCGWA, LNCRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNCUSUBS.DB_CONTENT.PROJ_NO;
        LNCRSUBS.FUNC = 'I';
        S000_CALL_LNZRSUBS();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNRSUBS);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCUSUBS.DB_CONTENT);
    }
    public void R000_TRANS_DATA_TO_LNRHSETN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRHSETN);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCUSUBS.DB_CONTENT);
