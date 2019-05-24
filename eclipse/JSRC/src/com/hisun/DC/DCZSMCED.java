package com.hisun.DC;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPRMT;
import com.hisun.DD.DDVMPRD;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.TD.TDCPRDP;

public class DCZSMCED {
    boolean pgmRtn = false;
    String K_PRDPR_TYPE = "PRDPR";
    String K_CON_MDEL = "IRDW";
    String K_HIS_CPB_NM = "DCCHMCED";
    String K_HIS_RMKS = "ICPRD PARM MAINTENANCE";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCHMCED DCCMCEDO = new DCCHMCED();
    DCCHMCED DCCMCEDN = new DCCHMCED();
    DCRICPRD DCRICPRD = new DCRICPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    TDCPRDP TDCPRDP = new TDCPRDP();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DDCUPARM DDCUPARM = new DDCUPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    SCCGWA SCCGWA;
    DCCSMCED DCCSMCED;
    public void MP(SCCGWA SCCGWA, DCCSMCED DCCSMCED) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSMCED = DCCSMCED;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSMCED return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSMCED.FUNC == 'I') {
            B020_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
            B040_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSMCED.FUNC == 'A') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B050_ADD_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (DCCSMCED.FUNC == 'M') {
            B010_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B070_MODIFY_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (DCCSMCED.FUNC == 'D') {
            B020_CHECK_INPUT_DATA();
            if (pgmRtn) return;
            B090_DELETE_PROCESS();
            if (pgmRtn) return;
            B110_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCSMCED.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSMCED.KEY.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.PROD_DSC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.EFFDAT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EFF_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.EFFDAT != SCCGWA.COMM_AREA.AC_DATE) {
                DCCSMCED.EFFDAT = SCCGWA.COMM_AREA.AC_DATE;
            }
        }
        if (DCCSMCED.EXPDAT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_EXP_DT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.EXPDAT != 20991231) {
                if ("20991231".trim().length() == 0) DCCSMCED.EXPDAT = 0;
                else DCCSMCED.EXPDAT = Integer.parseInt("20991231");
            }
        }
        if (DCCSMCED.CI_TYP == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.CI_TYP != '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CI_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSMCED.CCY.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (!DCCSMCED.CCY.equalsIgnoreCase("156")) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_INVAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSMCED.CCY_TYPE == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.CCY_TYPE != '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CCY_TYPE_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSMCED.PERM_KND == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_KND_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.PERM_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PER_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.PERM_MTH != '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PERM_MTH_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DCCSMCED.MRM_AMT == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MRM_AMT_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.INT_MTH == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_INT_MTH_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.PROD_DD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCCSMCED.PROD_DD;
            CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCUPARM);
            IBS.init(SCCGWA, DDVMPRD);
            DDCUPARM.TX_TYPE = 'I';
            DDCUPARM.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
            DDCUPARM.DATA.KEY.PRDMO_CD = "CAAC";
            DDCUPARM.DATA.KEY.PARM_CODE = BPCPQPRD.PARM_CODE;
            S000_CALL_DDZUPARM();
            if (pgmRtn) return;
            if (DDCUPARM.RC.RC_CODE != 0) {
                CEP.ERR(SCCGWA, DDCUPARM.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUPARM.DATA);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDVMPRD);
                CEP.TRC(SCCGWA, DDVMPRD);
            }
            B010_30_CHK_DD_PRD_DATA();
            if (pgmRtn) return;
        }
        if (DCCSMCED.DESC_DD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.PROD_TD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = DCCSMCED.PROD_TD;
            CEP.TRC(SCCGWA, BPCPQPRD.PRDT_CODE);
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            B010_40_CHK_TD_PRD_DATA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
            if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SPT_PRD_INVALID, BPCPQPRD.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            IBS.init(SCCGWA, TDCPRDP);
            BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
            if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
            BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
            if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
            JIBS_tmp_int = BPRPRMT.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
            if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
            JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
            for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
            BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
            BPCPRMM.FUNC = '3';
            BPCPRMM.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], TDCPRDP);
        }
        if (DCCSMCED.DESC_TD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_DSC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSMCED.DRAW_ORD == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DRAW_ORD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            if (DCCSMCED.DRAW_ORD != 'V') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DRAW_ORD_INVAL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_30_CHK_DD_PRD_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDVMPRD.VAL.CUST_TYPE);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_FIN_FLG);
        if (BPCPQPRD.CUS_COM_FLG == '1' 
            || BPCPQPRD.CUS_FIN_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DD_CI_TYP_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_40_CHK_TD_PRD_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.CUS_COM_FLG);
        CEP.TRC(SCCGWA, DCCSMCED.CI_TYP);
        if (DCCSMCED.CI_TYP == '2') {
            if (BPCPQPRD.CUS_COM_FLG != '0') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TD_CI_TYP_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSMCED.FUNC);
        CEP.TRC(SCCGWA, DCCSMCED.KEY.PROD_CD);
        if (DCCSMCED.KEY.PROD_CD.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_PROD_CD_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, DCRICPRD);
        BPRPRMT.KEY.TYP = K_PRDPR_TYPE;
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        BPRPRMT.KEY.CD = "999999" + BPRPRMT.KEY.CD.substring(6);
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (DCCSMCED.KEY.PROD_CD == null) DCCSMCED.KEY.PROD_CD = "";
        JIBS_tmp_int = DCCSMCED.KEY.PROD_CD.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) DCCSMCED.KEY.PROD_CD += " ";
        BPRPRMT.KEY.CD = BPRPRMT.KEY.CD.substring(0, 7 - 1) + DCCSMCED.KEY.PROD_CD + BPRPRMT.KEY.CD.substring(7 + 8 - 1);
        CEP.TRC(SCCGWA, "1");
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCRICPRD);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMM.FUNC = '3';
        BPCPRMM.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
