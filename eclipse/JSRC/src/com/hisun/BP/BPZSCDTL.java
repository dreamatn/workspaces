package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCDTL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "CURRENCY INFO MAINTAIN";
    String K_CPY_BPRPCDTL = "BPRPCDTL";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSCDTL_WS_CDTL_KEY WS_CDTL_KEY = new BPZSCDTL_WS_CDTL_KEY();
    BPZSCDTL_WS_CDTL_DETAIL WS_CDTL_DETAIL = new BPZSCDTL_WS_CDTL_DETAIL();
    char WS_TBL_CDTL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPCDTL BPRPCDTL = new BPRPCDTL();
    BPRPCDTL BPROCDTL = new BPRPCDTL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCOCDTO BPCOCDTO = new BPCOCDTO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BPCSCDTL BPCSCDTL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCDTL BPCSCDTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCDTL = BPCSCDTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCDTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCDTL.FUNC);
        if (BPCSCDTL.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCDTL.FUNC == 'A') {
            B020_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCDTL.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCDTL.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCDTL.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSCDTL.FUNC != 'B') {
            B060_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCSCDTL.FUNC_CODE == 'A') {
            BPRPCDTL.KEY.TYP = "PVAL ";
        } else {
            BPRPCDTL.KEY.TYP = "PVAL ";
            WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
            WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
            WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
            BPCPRMM.EFF_DT = BPCSCDTL.SDT;
            BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
            BPCPRMM.FUNC = '3';
            BPCPRMM.DAT_PTR = BPRPCDTL;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CDTL_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPCPRMM.EFF_DT = BPCSCDTL.SDT;
        BPCPRMM.EXP_DT = BPCSCDTL.EXP_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPCDTL.KEY.TYP = "PVAL ";
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPRPCDTL.DATA_LEN = 33;
        BPCPRMM.DAT_PTR = BPRPCDTL;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPCDTL;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        BPCPRMM.EFF_DT = BPCSCDTL.SDT;
        CEP.TRC(SCCGWA, BPCSCDTL.SDT);
        BPRPCDTL.KEY.TYP = "PVAL ";
        BPCPRMM.DAT_PTR = BPRPCDTL;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CDTL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPCDTL, BPROCDTL);
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPCPRMM.EFF_DT = BPCSCDTL.SDT;
        CEP.TRC(SCCGWA, BPCSCDTL.SDT);
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPCPRMM.EXP_DT = BPCSCDTL.EXP_DATE;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPCDTL.KEY.TYP = "PVAL ";
        BPCPRMM.FUNC = '2';
        BPRPCDTL.DATA_LEN = 33;
        BPCPRMM.DAT_PTR = BPRPCDTL;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPCDTL;
        BPCPNHIS.INFO.OLD_DAT_PT = BPROCDTL;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPCDTL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        BPCPRMM.EFF_DT = BPCSCDTL.SDT;
        BPRPCDTL.KEY.TYP = "PVAL ";
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        CEP.TRC(SCCGWA, BPRPCDTL.KEY.CD);
        BPCPRMM.DAT_PTR = BPRPCDTL;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CDTL_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        BPRPCDTL.KEY.TYP = "PVAL ";
        BPCPRMM.FUNC = '1';
        BPRPCDTL.DATA_LEN = 33;
        BPCPRMM.DAT_PTR = BPRPCDTL;
        B040_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B040_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPCDTL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, WS_CDTL_KEY);
        WS_CDTL_KEY.WS_CDTL_CCY = BPCSCDTL.CCY;
        CEP.TRC(SCCGWA, BPCSCDTL.CCY);
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCSCDTL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCSCDTL.M_FLG;
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        IBS.init(SCCGWA, BPCPRMB);
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = "PVAL";
        BPRPRMT.KEY.CD = BPRPCDTL.KEY.CD;
        BPRPCDTL.KEY.TYP = "PVAL ";
        T000_STARTBR_BPTPARM();
        if (pgmRtn) return;
        T000_READNEXT_BPTPARM();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPRPCDTL.KEY.CD, WS_CDTL_KEY);
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_TBL_CDTL_FLAG != 'N' 
            && BPRPCDTL.KEY.TYP.equalsIgnoreCase("PVAL) BPRPCDTL.KEY.TYP.equalsIgnoreCase(") 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            if ((!WS_CDTL_KEY.WS_CDTL_CCY.equalsIgnoreCase(BPCSCDTL.CCY) 
                && BPCSCDTL.CCY.trim().length() > 0) 
                || (WS_CDTL_KEY.WS_CDTL_PAR_VAL != BPCSCDTL.PAR_VAL 
                && BPCSCDTL.PAR_VAL != 0) 
                || (WS_CDTL_KEY.WS_CDTL_M_FLG != BPCSCDTL.M_FLG 
                && BPCSCDTL.M_FLG != ' ')) {
            } else {
                B050_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTPARM();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRPCDTL.KEY.CD, WS_CDTL_KEY);
            CEP.TRC(SCCGWA, WS_CDTL_KEY);
        }
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL);
        T000_ENDBR_BPTPARM();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 119;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 7;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_CDTL_DETAIL.WS_CCY = WS_CDTL_KEY.WS_CDTL_CCY;
        WS_CDTL_DETAIL.WS_PAR_VAL = WS_CDTL_KEY.WS_CDTL_PAR_VAL;
        WS_CDTL_DETAIL.WS_M_FLG = WS_CDTL_KEY.WS_CDTL_M_FLG;
        WS_CDTL_DETAIL.WS_R_FLG = BPRPCDTL.DATA_TXT.R_FLG;
        WS_CDTL_DETAIL.WS_CDTL_DESC = BPRPCDTL.DESC;
        WS_CDTL_DETAIL.WS_CDTL_CDESC = BPRPCDTL.CDSC;
        WS_CDTL_DETAIL.WS_CDTL_EFF_DATE = BPRPCDTL.DATA_TXT.EFF_DT;
        CEP.TRC(SCCGWA, "BPZSCDTL123");
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CCY);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_PAR_VAL);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_M_FLG);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_R_FLG);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CDTL_DESC);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CDTL_CDESC);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CDTL_EFF_DATE);
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CDTL_EFF_DATE);
        WS_CDTL_DETAIL.WS_CDTL_EXP_DATE = BPRPCDTL.DATA_TXT.EXP_DT;
        CEP.TRC(SCCGWA, WS_CDTL_DETAIL.WS_CDTL_EXP_DATE);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_CDTL_DETAIL);
        SCCMPAG.DATA_LEN = 119;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCDTO.EXP_DATE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSCDTL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOCDTO;
        SCCFMT.DATA_LEN = 140;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            BPRPCDTL.KEY.TYP = BPRPARM.KEY.TYPE;
            BPRPCDTL.KEY.CD = BPRPARM.KEY.CODE;
            BPRPCDTL.DESC = BPRPARM.DESC;
            BPRPCDTL.CDSC = BPRPARM.CDESC;
            IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPRPCDTL.DATA_TXT);
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_BRW, BPCPRMB);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        BPRPCDTL.DATA_TXT.R_FLG = BPCSCDTL.R_FLG;
        BPRPCDTL.DATA_TXT.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRPCDTL.DATA_TXT.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRPCDTL.DESC = BPCSCDTL.DESC;
        BPRPCDTL.CDSC = BPCSCDTL.CDSC;
        BPRPCDTL.DATA_TXT.EFF_DT = BPCSCDTL.SDT;
        BPRPCDTL.DATA_TXT.EXP_DT = BPCSCDTL.EXP_DATE;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSCDTL.FUNC == 'I') {
            BPCOCDTO.FUNC = BPCSCDTL.FUNC_CODE;
        } else {
            BPCOCDTO.FUNC = BPCSCDTL.FUNC;
        }
        BPCOCDTO.PARM_TP = BPRPCDTL.KEY.TYP;
        IBS.CPY2CLS(SCCGWA, BPRPCDTL.KEY.CD, WS_CDTL_KEY);
        BPCOCDTO.DESC = BPRPCDTL.DESC;
        BPCOCDTO.CDSC = BPRPCDTL.CDSC;
        BPCOCDTO.CCY = WS_CDTL_KEY.WS_CDTL_CCY;
        BPCOCDTO.PAR_VAL = WS_CDTL_KEY.WS_CDTL_PAR_VAL;
        BPCOCDTO.M_FLG = WS_CDTL_KEY.WS_CDTL_M_FLG;
        BPCOCDTO.R_FLG = BPRPCDTL.DATA_TXT.R_FLG;
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPCOCDTO.SDT = BPCSCDTL.SDT;
        BPCOCDTO.EXP_DATE = BPCPRMM.EXP_DT;
        BPCOCDTO.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCOCDTO.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, BPCOCDTO.PAR_VAL);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MT, BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTPARM() throws IOException,SQLException,Exception {
        BPCPRMB.DAT_PTR = BPRPRMT;
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTPARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        if (BPCPRMB.RC.RC_RTNCODE != 0) {
            WS_TBL_CDTL_FLAG = 'N';
        } else {
            BPRPCDTL.KEY.TYP = BPRPRMT.KEY.TYP;
            BPRPCDTL.KEY.CD = BPRPRMT.KEY.CD;
            BPRPCDTL.DESC = BPRPRMT.DESC;
            BPRPCDTL.CDSC = BPRPRMT.CDESC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPCDTL.DATA_TXT);
        }
    }
    public void T000_ENDBR_BPTPARM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
