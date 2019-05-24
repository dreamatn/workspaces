package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRGCM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_BRW = "BP-PARM-BROWSE      ";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "REGION CODE INFO MAINTAIN";
    String K_CPY_BPRPRGCM = "BPRPRGCM";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSRGCM_WS_RGNC_HEAD WS_RGNC_HEAD = new BPZSRGCM_WS_RGNC_HEAD();
    BPZSRGCM_WS_RGNC_DETAIL WS_RGNC_DETAIL = new BPZSRGCM_WS_RGNC_DETAIL();
    BPZSRGCM_WS_RGNC_KEY WS_RGNC_KEY = new BPZSRGCM_WS_RGNC_KEY();
    int WS_LAST_SEQ = 0;
    char WS_TBL_RGNC_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRPRGCM BPRPRGCM = new BPRPRGCM();
    BPRPRGCM BPRORGCM = new BPRPRGCM();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCORGNC BPCORGNC = new BPCORGNC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    BPCSRGCM BPCSRGCM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSRGCM BPCSRGCM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRGCM = BPCSRGCM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSRGCM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSRGCM.FUNC);
        if (BPCSRGCM.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRGCM.FUNC == 'A') {
            T000_CHECK_EXPDATE();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRGCM.FUNC == 'M') {
            T000_CHECK_EXPDATE();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRGCM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRGCM.FUNC == 'D') {
            B060_DELETE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSRGCM.FUNC != 'B') {
            B040_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void T000_CHECK_EXPDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "RGNTP";
        BPRPARM.KEY.CODE = BPCSRGCM.RGN_TYPE;
        CEP.TRC(SCCGWA, BPCSRGCM.RGN_TYPE);
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSRGCM.EXP_DATE);
        CEP.TRC(SCCGWA, BPRPARM);
        CEP.TRC(SCCGWA, BPRPARM.EXP_DATE);
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        if (BPCSRGCM.EXP_DATE > BPRPARM.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMM);
        if (BPCSRGCM.FUNC_CODE == 'A') {
            BPRPRGCM.KEY.TYP = "RGNCD";
        } else {
            BPRPRGCM.KEY.TYP = "RGNCD";
            WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
            WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
            WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
            BPCPRMM.EFF_DT = BPCSRGCM.EFF_DATE;
            BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
            BPCPRMM.FUNC = '3';
            CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXXXXXXXX:");
            CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
            CEP.TRC(SCCGWA, BPRPRGCM.KEY.TYP);
            CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            BPCPRMM.DAT_PTR = BPRPRGCM;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_NO_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '0';
        BPCPRMM.EFF_DT = BPCSRGCM.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSRGCM.EXP_DATE;
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPRPRGCM.DATA_LEN = 180;
        BPCPRMM.DAT_PTR = BPRPRGCM;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPRGCM;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMM.EFF_DT = BPCSRGCM.EFF_DATE;
        BPCPRMM.DAT_PTR = BPRPRGCM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_NO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRPRGCM, BPRORGCM);
        BPCPRMM.EFF_DT = BPCSRGCM.EFF_DATE;
        BPCPRMM.EXP_DT = BPCSRGCM.EXP_DATE;
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMM.FUNC = '2';
        BPRPRGCM.DATA_LEN = 180;
        BPCPRMM.DAT_PTR = BPRPRGCM;
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
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPRGCM;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRORGCM;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRPRGCM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSRGCM.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCORGNC;
        SCCFMT.DATA_LEN = 263;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMB);
        BPRPARM.KEY.TYPE = " ";
        IBS.init(SCCGWA, WS_RGNC_KEY);
        BPRPARM.KEY.CODE = " ";
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        CEP.TRC(SCCGWA, BPCSRGCM.BNK);
        CEP.TRC(SCCGWA, BPCSRGCM.RGN_TYPE);
        CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMB.FUNC = '0';
        BPCPRMB.DAT_PTR = BPRPRGCM;
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPRPRGCM.KEY.CD, WS_RGNC_KEY);
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        B050_02_OUTPUT_HEADLINE();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
        for (WS_CNT = 1; !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            && BPRPRGCM.KEY.TYP.equalsIgnoreCase("RGNCD") 
            && WS_RGNC_KEY.WS_RGNC_BK.equalsIgnoreCase(BPCSRGCM.BNK) 
            && !(!WS_RGNC_KEY.WS_RGNC_TYPE.equalsIgnoreCase(BPCSRGCM.RGN_TYPE) 
            || BPCSRGCM.RGN_TYPE.trim().length() <= 0) 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B050_03_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRPRGCM.KEY.CD, WS_RGNC_KEY);
            CEP.TRC(SCCGWA, WS_RGNC_KEY);
            CEP.TRC(SCCGWA, BPCSRGCM.RGN_TYPE);
        }
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 105;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
        WS_RGNC_HEAD.WS_RGNC_BANK = BPCSRGCM.BNK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RGNC_HEAD);
        SCCMPAG.DATA_LEN = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        WS_RGNC_DETAIL.WS_RGNC_RGN_TYPE = WS_RGNC_KEY.WS_RGNC_TYPE;
        WS_RGNC_DETAIL.WS_RGNC_RGN_SEQ = WS_RGNC_KEY.WS_RGNC_SEQ;
        WS_RGNC_DETAIL.WS_RGNC_DESC = BPRPRGCM.DESC;
        WS_RGNC_DETAIL.WS_RGNC_CDESC = BPRPRGCM.CDSC;
        WS_RGNC_DETAIL.WS_RGNC_EFF_DATE = BPRPRGCM.DATA_TXT.EFF_DT;
        WS_RGNC_DETAIL.WS_RGNC_EXP_DATE = BPRPRGCM.DATA_TXT.EXP_DT;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RGNC_DETAIL);
        SCCMPAG.DATA_LEN = 105;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMM);
        BPCPRMM.FUNC = '4';
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMM.EFF_DT = BPCSRGCM.EFF_DATE;
        BPCPRMM.DAT_PTR = BPRPRGCM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_NO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_RGNC_KEY.WS_RGNC_BK = BPCSRGCM.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCSRGCM.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = (short) BPCSRGCM.RGN_SEQ;
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMM.FUNC = '1';
        BPRPRGCM.DATA_LEN = 180;
        BPCPRMM.DAT_PTR = BPRPRGCM;
        B060_01_HISTORY_RECORD();
        if (pgmRtn) return;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B060_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRPRGCM;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        BPRPRGCM.DATA_TXT.CALD_CD = BPCSRGCM.CALD_CD;
        BPRPRGCM.DATA_TXT.RMK = BPCSRGCM.REMARK;
        BPRPRGCM.DESC = BPCSRGCM.DESC;
        BPRPRGCM.CDSC = BPCSRGCM.CDSC;
        CEP.TRC(SCCGWA, BPCSRGCM.DESC);
        CEP.TRC(SCCGWA, BPCSRGCM.CDSC);
        CEP.TRC(SCCGWA, BPCSRGCM.CALD_CD);
        if (BPCSRGCM.EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            BPRPRGCM.DATA_TXT.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            BPRPRGCM.DATA_TXT.EFF_DT = BPCSRGCM.EFF_DATE;
        }
        BPRPRGCM.DATA_TXT.EXP_DT = BPCSRGCM.EXP_DATE;
        BPRPRGCM.DATA_TXT.OPN_TM = BPCSRGCM.OPEN_TM;
        BPRPRGCM.DATA_TXT.CLS_TM = BPCSRGCM.CLOSE_TM;
        BPRPRGCM.DATA_TXT.HOPN_TM = BPCSRGCM.HOPEN_TM;
        BPRPRGCM.DATA_TXT.HCLS_TM = BPCSRGCM.HCLOS_TM;
        BPRPRGCM.DATA_TXT.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRPRGCM.DATA_TXT.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCSRGCM.FUNC == 'I') {
            BPCORGNC.FUNC = BPCSRGCM.FUNC_CODE;
        } else {
            BPCORGNC.FUNC = BPCSRGCM.FUNC;
        }
        BPCORGNC.PARM_TP = BPRPRGCM.KEY.TYP;
        IBS.CPY2CLS(SCCGWA, BPRPRGCM.KEY.CD, WS_RGNC_KEY);
        BPCORGNC.RGN_DESC = BPRPRGCM.DESC;
        BPCORGNC.RGN_CDSC = BPRPRGCM.CDSC;
        CEP.TRC(SCCGWA, BPCORGNC.RGN_DESC);
        CEP.TRC(SCCGWA, BPCORGNC.RGN_CDSC);
        BPCORGNC.BNK = WS_RGNC_KEY.WS_RGNC_BK;
        BPCORGNC.RGN_TYPE = WS_RGNC_KEY.WS_RGNC_TYPE;
        BPCORGNC.RGN_SEQ = WS_RGNC_KEY.WS_RGNC_SEQ;
        BPCORGNC.CALD_CD = BPRPRGCM.DATA_TXT.CALD_CD;
        BPCORGNC.REMARK = BPRPRGCM.DATA_TXT.RMK;
        CEP.TRC(SCCGWA, BPCORGNC.REMARK);
        BPCORGNC.EFF_DATE = BPCPRMM.EFF_DT;
        BPCORGNC.EXP_DATE = BPCPRMM.EXP_DT;
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        BPCORGNC.OPEN_TM = BPRPRGCM.DATA_TXT.OPN_TM;
        BPCORGNC.CLOSE_TM = BPRPRGCM.DATA_TXT.CLS_TM;
        BPCORGNC.HOPEN_TM = BPRPRGCM.DATA_TXT.HOPN_TM;
        BPCORGNC.HCLOS_TM = BPRPRGCM.DATA_TXT.HCLS_TM;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
