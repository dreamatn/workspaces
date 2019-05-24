package com.hisun.TD;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICMAGT;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCPACTY;
import com.hisun.DC.DCRACLNK;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class TDZSMAGT {
    boolean pgmRtn = false;
    String K_I_OUTPUT_FMT = "TD180";
    String K_A_OUTPUT_FMT = "TD181";
    String K_M_OUTPUT_FMT = "TD182";
    String K_D_OUTPUT_FMT = "TD183";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CNT = 0;
    String WS_CARD_NO = " ";
    String WS_VIA_AC = " ";
    char WS_TDTMAGT_REC = ' ';
    char WS_TDTCMST_REC = ' ';
    char WS_TDTSMST_REC = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCOMAGT TDCOMAGT = new TDCOMAGT();
    TDCOMAGT TDCNMAGT = new TDCOMAGT();
    CICACCU CICACCU = new CICACCU();
    CICUAGT CICUAGT = new CICUAGT();
    CICMAGT CICCAGT = new CICMAGT();
    TDRMAGT TDRMAGT = new TDRMAGT();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCRACLNK DCRACLNK = new DCRACLNK();
    CICQACRI CICQACRI = new CICQACRI();
    SCCGWA SCCGWA;
    TDCSMAGT TDCSMAGT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, TDCSMAGT TDCSMAGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSMAGT = TDCSMAGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZSMAGT return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, TDCSMAGT.FUNC);
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_TYPE);
        if (TDCSMAGT.FUNC == 'I') {
            B020_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (TDCSMAGT.FUNC == 'A') {
            B030_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (TDCSMAGT.FUNC == 'M') {
            B040_UPDATE_PROCESS();
            if (pgmRtn) return;
        } else if (TDCSMAGT.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + TDCSMAGT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDCSMAGT.FUNC != 'A' 
            && TDCSMAGT.FUNC != 'M' 
            && TDCSMAGT.FUNC != 'D' 
            && TDCSMAGT.FUNC != 'I' 
            && TDCSMAGT.FUNC != 'B') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR);
        }
        if (TDCSMAGT.EFF_DATE != 0 
            && TDCSMAGT.EXP_DATE != 0) {
            if (TDCSMAGT.EFF_DATE > TDCSMAGT.EXP_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_EFFDT);
            }
            if (TDCSMAGT.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_EXPDT_LESS_ACDT);
            }
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCSMAGT.AC;
        S000_LINK_CIZACCU();
        if (pgmRtn) return;
        TDCSMAGT.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, TDCSMAGT.AC);
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.DATA.AGR_NO = TDCSMAGT.AC;
        CICQACRI.FUNC = 'A';
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        WS_VIA_AC = TDCSMAGT.AC;
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("TD")) {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCSMAGT.AC;
            T000_READ_TDTCMST();
            if (pgmRtn) return;
            if (TDRCMST.BV_TYP != '1' 
                && TDRCMST.BV_TYP != '3' 
                && TDRCMST.BV_TYP != '7') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
            }
            if (TDRCMST.BV_TYP == '3') {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
                T000_READ_TDTSMST();
                if (pgmRtn) return;
                if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("020")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_INV_AC_TYP);
                }
            }
        }
    }
    public void B020_QUERY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BUG1");
        CEP.TRC(SCCGWA, TDCSMAGT.AC);
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_NO);
        IBS.init(SCCGWA, TDRMAGT);
        TDRMAGT.KEY.AC_NO = WS_VIA_AC;
        T000_READ_TDTMAGT_BY_VA();
        if (pgmRtn) return;
        if (WS_TDTMAGT_REC == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AGT_NOT_FOUND);
        }
        CEP.TRC(SCCGWA, TDRMAGT.AGT_TYP);
        IBS.init(SCCGWA, TDCOMAGT);
        TDCOMAGT.AC = TDCSMAGT.AC;
        TDCOMAGT.AC_SEQ = TDCSMAGT.AC_SEQ;
        TDCOMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCOMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCOMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCOMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCOMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCOMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCOMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCOMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCOMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCOMAGT.REMARK = TDRMAGT.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_I_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOMAGT;
        SCCFMT.DATA_LEN = 263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_ADD_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BUG1");
        CEP.TRC(SCCGWA, TDCSMAGT.AC);
        B030_01_WRITE_TDTMAGT_PROC();
        if (pgmRtn) return;
        B030_03_WRITE_CITAGT_PROC();
        if (pgmRtn) return;
        B030_05_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        B040_01_REWRITE_TDTMAGT_PROC();
        if (pgmRtn) return;
        B040_02_WRITE_NHFIS();
        if (pgmRtn) return;
        B040_03_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B050_03_DELETE_TDTMAGT_PROC();
        if (pgmRtn) return;
        B050_01_CANCEL_CITAGT_PROC();
        if (pgmRtn) return;
        B050_05_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B030_03_WRITE_CITAGT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_TYPE);
        IBS.init(SCCGWA, CICCAGT);
        if (WS_TDTMAGT_REC == 'N') {
            CICCAGT.FUNC = 'A';
        } else {
            CICCAGT.FUNC = 'M';
        }
        CICCAGT.DATA.CI_NO = TDRMAGT.CI_NO;
        CICCAGT.DATA.ENTY_NO = TDCSMAGT.AC;
        CICCAGT.DATA.AGT_NO = TDRMAGT.KEY.AGT_NO;
        CICCAGT.DATA.AGT_TYP = TDRMAGT.AGT_TYP;
        CICCAGT.DATA.ENTY_TYP = CICACCU.DATA.ENTY_TYP;
        CICCAGT.DATA.FRM_APP = "TD";
        CICCAGT.DATA.AGT_LVL = 'A';
        CICCAGT.DATA.EFF_DATE = TDRMAGT.EFF_DATE;
        CICCAGT.DATA.EXP_DATE = TDRMAGT.EXP_DATE;
        CICCAGT.DATA.AGT_STS = TDRMAGT.AGT_STS;
        CICCAGT.DATA.REMARK = TDRMAGT.REMARK;
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B030_01_WRITE_TDTMAGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRMAGT);
        TDRMAGT.KEY.AC_NO = WS_VIA_AC;
        T000_READ_TDTMAGT_BY_VA();
        if (pgmRtn) return;
        if (WS_TDTMAGT_REC == 'Y') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AGT_IS_FOUND);
        }
        R000_GEI_AGT_NO_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, TDRMAGT);
        TDRMAGT.KEY.AC_NO = WS_VIA_AC;
        TDRMAGT.KEY.AGT_NO = TDCSMAGT.AGT_NO;
        T000_READ_TDTMAGT();
        if (pgmRtn) return;
        if (WS_TDTMAGT_REC == 'N') {
            TDRMAGT.CI_NO = CICACCU.DATA.CI_NO;
            TDRMAGT.AGT_TYP = TDCSMAGT.AGT_TYPE;
            TDRMAGT.AGT_LVL = 'A';
            TDRMAGT.EFF_DATE = TDCSMAGT.EFF_DATE;
            TDRMAGT.EXP_DATE = TDCSMAGT.EXP_DATE;
            TDRMAGT.AGT_TRAN_NUM = TDCSMAGT.AGT_TNUM;
            TDRMAGT.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRMAGT.AGT_STS = 'N';
            TDRMAGT.SGN_CHNL = SCCGWA.COMM_AREA.CHNL;
            TDRMAGT.REMARK = WS_CARD_NO;
            TDRMAGT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRMAGT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            TDRMAGT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRMAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRMAGT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRMAGT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_TDTMAGT();
            if (pgmRtn) return;
        } else {
            if (TDRMAGT.AGT_STS == 'C') {
                TDRMAGT.AGT_STS = 'N';
                TDRMAGT.AGT_TRAN_NUM = TDCSMAGT.AGT_TNUM;
                TDRMAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                TDRMAGT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRMAGT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRMAGT.EFF_DATE = TDCSMAGT.EFF_DATE;
                TDRMAGT.EXP_DATE = TDCSMAGT.EXP_DATE;
                TDRMAGT.SGN_DATE = SCCGWA.COMM_AREA.AC_DATE;
                T000_REWRITE_TDTMAGT();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_05_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMAGT);
        TDCOMAGT.AC = TDCSMAGT.AC;
        TDCOMAGT.AC_SEQ = TDCSMAGT.AC_SEQ;
        TDCOMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCOMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCOMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCOMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCOMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCOMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCOMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCOMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCOMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCOMAGT.REMARK = TDRMAGT.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_A_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOMAGT;
        SCCFMT.DATA_LEN = 263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B040_01_REWRITE_TDTMAGT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_NO);
        CEP.TRC(SCCGWA, TDCSMAGT.AC);
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_NO);
        IBS.init(SCCGWA, TDRMAGT);
        TDRMAGT.KEY.AC_NO = WS_VIA_AC;
        T000_READ_TDTMAGT_UPD_VA();
        if (pgmRtn) return;
        R000_GET_TDTMAGT_OLD();
        if (pgmRtn) return;
        if (WS_TDTMAGT_REC == 'N') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AGT_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRMAGT.AGT_STS == 'C') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AGT_IS_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        TDRMAGT.AGT_TRAN_NUM = TDCSMAGT.AGT_TNUM;
        TDRMAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRMAGT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRMAGT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_TDTMAGT();
        if (pgmRtn) return;
        R000_GET_TDTMAGT_NEW();
        if (pgmRtn) return;
    }
    public void B040_02_WRITE_NHFIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (CICACCU.DATA.ENTY_TYP == '2') {
            BPCPNHIS.INFO.TX_TOOL = TDCSMAGT.AC;
        }
        BPCPNHIS.INFO.AC = TDCSMAGT.AC;
        BPCPNHIS.INFO.AC_SEQ = TDCSMAGT.AC_SEQ;
        BPCPNHIS.INFO.CI_NO = TDRMAGT.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "TDCOMAGT";
        BPCPNHIS.INFO.FMT_ID_LEN = 263;
        BPCPNHIS.INFO.TX_RMK = "CHANGE AGT TRAN NUM INFO";
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = TDCOMAGT;
        BPCPNHIS.INFO.NEW_DAT_PT = TDCNMAGT;
        S00_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_03_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMAGT);
        TDCOMAGT.AC = TDCSMAGT.AC;
        TDCOMAGT.AC_SEQ = TDCSMAGT.AC_SEQ;
        TDCOMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCOMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCOMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCOMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCOMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCOMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCOMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCOMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCOMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCOMAGT.REMARK = TDRMAGT.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_M_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOMAGT;
        SCCFMT.DATA_LEN = 263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_01_CANCEL_CITAGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCAGT);
        CICCAGT.FUNC = 'D';
        CICCAGT.DATA.CI_NO = TDRMAGT.CI_NO;
        CICCAGT.DATA.AGT_NO = TDRMAGT.KEY.AGT_NO;
        CICCAGT.DATA.ENTY_NO = TDCSMAGT.AC;
        CICCAGT.DATA.ENTY_TYP = CICACCU.DATA.ENTY_TYP;
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_TYPE);
        CICCAGT.DATA.AGT_TYP = TDCSMAGT.AGT_TYPE;
        S000_CALL_CIZMAGT();
        if (pgmRtn) return;
    }
    public void B050_03_DELETE_TDTMAGT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRMAGT);
        CEP.TRC(SCCGWA, TDCSMAGT.AGT_NO);
        CEP.TRC(SCCGWA, TDCSMAGT.AC);
        CEP.TRC(SCCGWA, WS_VIA_AC);
        TDRMAGT.KEY.AC_NO = WS_VIA_AC;
        T000_READ_TDTMAGT_UPD_VA();
        if (pgmRtn) return;
        if (WS_TDTMAGT_REC == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AGT_NOT_FOUND);
        }
        if (TDRMAGT.AGT_STS == 'C') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AGT_IS_CANCEL);
        }
        TDRMAGT.AGT_STS = 'C';
        TDRMAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRMAGT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRMAGT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_TDTMAGT();
        if (pgmRtn) return;
    }
    public void R000_GEI_AGT_NO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICUAGT);
        CICUAGT.DATA.AGT_TYP = TDCSMAGT.AGT_TYPE;
        CICUAGT.DATA.ENTY_NO = TDCSMAGT.AC;
        CICUAGT.DATA.ENTY_TYP = CICACCU.DATA.ENTY_TYP;
        S000_LINK_CIZUAGT();
        if (pgmRtn) return;
        TDCSMAGT.AGT_NO = CICUAGT.DATA.AGT_NO;
    }
    public void R000_GET_TDTMAGT_OLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMAGT);
        TDCOMAGT.AC = TDRMAGT.KEY.AC_NO;
        TDCOMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCOMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCOMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCOMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCOMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCOMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCOMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCOMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCOMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCOMAGT.REMARK = TDRMAGT.REMARK;
    }
    public void R000_GET_TDTMAGT_NEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCNMAGT);
        TDCNMAGT.AC = TDRMAGT.KEY.AC_NO;
        TDCNMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCNMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCNMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCNMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCNMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCNMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCNMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCNMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCNMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCNMAGT.REMARK = TDRMAGT.REMARK;
    }
    public void T000_READ_TDTMAGT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRMAGT.KEY.AC_NO);
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        TDTMAGT_RD.where = "AC_NO = :TDRMAGT.KEY.AC_NO "
            + "AND AGT_NO = :TDRMAGT.KEY.AGT_NO";
        TDTMAGT_RD.upd = true;
        IBS.READ(SCCGWA, TDRMAGT, this, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTMAGT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTMAGT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTMAGT_BY_CINO() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        TDTMAGT_RD.where = "CI_NO = :TDRMAGT.CI_NO "
            + "AND AGT_STS = 'N'";
        TDTMAGT_RD.fst = true;
        IBS.READ(SCCGWA, TDRMAGT, this, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTMAGT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTMAGT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTCMST_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTCMST_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTCMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRSMST.AC_NO);
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "AND ACO_STS = '0'";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTSMST_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B050_05_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOMAGT);
        TDCOMAGT.AC = TDCSMAGT.AC;
        TDCOMAGT.AC_SEQ = TDCSMAGT.AC_SEQ;
        TDCOMAGT.AGT_NO = TDRMAGT.KEY.AGT_NO;
        TDCOMAGT.CI_NO = TDRMAGT.CI_NO;
        TDCOMAGT.AGT_TYPE = TDRMAGT.AGT_TYP;
        TDCOMAGT.AGT_LVL = TDRMAGT.AGT_LVL;
        TDCOMAGT.EFF_DATE = TDRMAGT.EFF_DATE;
        TDCOMAGT.EXP_DATE = TDRMAGT.EXP_DATE;
        TDCOMAGT.AGT_TNUM = TDRMAGT.AGT_TRAN_NUM;
        TDCOMAGT.SGN_DATE = TDRMAGT.SGN_DATE;
        TDCOMAGT.AGT_STS = TDRMAGT.AGT_STS;
        TDCOMAGT.REMARK = TDRMAGT.REMARK;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_D_OUTPUT_FMT;
        SCCFMT.DATA_PTR = TDCOMAGT;
        SCCFMT.DATA_LEN = 263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_WRITE_TDTMAGT() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        IBS.WRITE(SCCGWA, TDRMAGT, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTMAGT_UPD() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        TDTMAGT_RD.upd = true;
        IBS.READ(SCCGWA, TDRMAGT, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTMAGT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTMAGT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTMAGT_UPD_VA() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        TDTMAGT_RD.where = "AC_NO = :TDRMAGT.KEY.AC_NO "
            + "AND AGT_STS = 'N'";
        TDTMAGT_RD.upd = true;
        IBS.READ(SCCGWA, TDRMAGT, this, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTMAGT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTMAGT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_TDTMAGT_BY_VA() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        TDTMAGT_RD.where = "AC_NO = :TDRMAGT.KEY.AC_NO "
            + "AND AGT_STS = 'N'";
        IBS.READ(SCCGWA, TDRMAGT, this, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTMAGT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTMAGT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_TDTMAGT() throws IOException,SQLException,Exception {
        TDTMAGT_RD = new DBParm();
        TDTMAGT_RD.TableName = "TDTMAGT";
        IBS.REWRITE(SCCGWA, TDRMAGT, TDTMAGT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTMAGT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTACLNK_FIRST() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.where = "VIA_AC = :DCRACLNK.VIA_AC "
            + "AND CARD_AC_STATUS = '1'";
        DCTACLNK_RD.fst = true;
        IBS.READ(SCCGWA, DCRACLNK, this, DCTACLNK_RD);
    }
    public void S000_LINK_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_LINK_CIZUAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-GNL-AGT-NO", CICUAGT);
        if (CICUAGT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICUAGT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZMAGT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-AGT", CICCAGT);
        if (CICCAGT.RC.RC_CODE != 0) {
