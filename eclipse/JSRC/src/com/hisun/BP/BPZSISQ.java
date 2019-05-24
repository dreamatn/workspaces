package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;

public class BPZSISQ {
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY      ";
    String CPN_R_SQTPH_B = "BP-R-SQTPH-B        ";
    String CPN_R_EXRD_M = "BP-R-EXRD-M         ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE     ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short K_MAX_ARRAY = 30;
    short WS_IDX = 0;
    String WS_BASE_CCY = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCERTC BPCERTC = new BPCERTC();
    BPRSQTPH BPRSQTPH = new BPRSQTPH();
    BPCRQTPH BPCRQTPH = new BPCRQTPH();
    BPREXRD BPREXRD = new BPREXRD();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCOIEC BPCOIEC = new BPCOIEC();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPCOISQ BPCOISQ;
    public void MP(SCCGWA SCCGWA, BPCOISQ BPCOISQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOISQ = BPCOISQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSISQ return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOISQ.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCOISQ.DATA_INFO.FUNC == '1') {
            B020_GET_SQTPH_INFO();
            if (pgmRtn) return;
        } else if (BPCOISQ.DATA_INFO.FUNC == '2') {
            B030_GET_SQTPH_INFO();
            if (pgmRtn) return;
        } else if (BPCOISQ.DATA_INFO.FUNC == '3') {
            B040_GET_SQTPH_INFO();
            if (pgmRtn) return;
        } else if (BPCOISQ.DATA_INFO.FUNC == '4') {
            B050_GET_SQTPH_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOISQ.DATA_INFO.FUNC == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_FUNC, BPCOISQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if ((BPCOISQ.DATA_INFO.FUNC != '1' 
                && BPCOISQ.DATA_INFO.FUNC != '2' 
                && BPCOISQ.DATA_INFO.FUNC != '3' 
                && BPCOISQ.DATA_INFO.FUNC != '4')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_FUNC, BPCOISQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCOISQ.DATA_INFO.SQTP_TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_EXR_TYPE, BPCOISQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCERTC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCERTC.KEY.TYP = "EXRT";
            BPCERTC.KEY.CD = BPCOISQ.DATA_INFO.SQTP_TYPE;
            BPCPRMR.DAT_PTR = BPCERTC;
            IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ERR_EXR_TYPE, BPCOISQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCERTC.DATA_TXT.SUM_IND != '0') {
