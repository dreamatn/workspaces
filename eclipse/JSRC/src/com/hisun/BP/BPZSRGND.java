package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSRGND {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_RD_MT = "BP-R-RGND-MAINTAIN  ";
    String CPN_R_BRW_RGND = "BP-R-INQ-BRGN ";
    String CPN_REC_NHIS = "BP-REC-NHIS  ";
    String CPN_BP_PARM_READ = "BP-PARM-READ  ";
    String CPN_P_INQ_ORG_STATION = "BP-P-INQ-ORG-STATION";
    int K_MAX_ROW = 99;
    String K_HIS_REMARKS = "REGION DETAILS INFO ";
    String K_HIS_COPYBOOK_NAME = "BPRRGND";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZSRGND_WS_RGND_HEAD WS_RGND_HEAD = new BPZSRGND_WS_RGND_HEAD();
    BPZSRGND_WS_RGND_DETAIL WS_RGND_DETAIL = new BPZSRGND_WS_RGND_DETAIL();
    char WS_OLD_FLG = ' ';
    String WS_OLD_RMK = " ";
    int WS_OLD_EFF_DT = 0;
    int WS_OLD_EXP_DT = 0;
    BPZSRGND_WS_HIS_REF WS_HIS_REF = new BPZSRGND_WS_HIS_REF();
    BPZSRGND_WS_CODE WS_CODE = new BPZSRGND_WS_CODE();
    char WS_TBL_RGND_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRRGND BPRRGND = new BPRRGND();
    BPRRGND BPRORGND = new BPRRGND();
    BPRPRGTP BPRPRGTP = new BPRPRGTP();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCRRGDM BPCRRGDM = new BPCRRGDM();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPCORGDO BPCORGDO = new BPCORGDO();
    BPCPQRGN BPCPQRGN = new BPCPQRGN();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRGST BPCPRGST = new BPCPRGST();
    SCCGWA SCCGWA;
    BPCSRGND BPCSRGND;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSRGND BPCSRGND) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSRGND = BPCSRGND;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSRGND return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRRGND);
        IBS.init(SCCGWA, BPCRRGDM);
        IBS.init(SCCGWA, BPCSRGND.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSRGND.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSRGND.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCSRGND.FUNC == 'M') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B030_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCSRGND.FUNC == 'D') {
            B060_DELETE_PROCESS();
            if (pgmRtn) return;
            B060_01_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (BPCSRGND.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSRGND.FUNC != 'B') {
            B040_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        if (BPCSRGND.FUNC_CODE == 'A') {
            BPCSRGND.FUNC = BPCSRGND.FUNC_CODE;
        } else {
            CEP.TRC(SCCGWA, BPCSRGND.FUNC_CODE);
            BPCSRGND.FUNC = BPCSRGND.FUNC_CODE;
            CEP.TRC(SCCGWA, BPCSRGND.BNK);
            BPRRGND.KEY.BNK = BPCSRGND.BNK;
            CEP.TRC(SCCGWA, BPCSRGND.RGN_TYPE);
            BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
            CEP.TRC(SCCGWA, BPCSRGND.RGN_SEQ);
            BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
            BPRRGND.KEY.RGN_UNIT = BPCSRGND.UNIT;
            BPCRRGDM.INFO.FUNC = 'Q';
            BPCRRGDM.INFO.POINTER = BPRRGND;
            BPCRRGDM.DATA_LEN = 184;
            S000_CALL_BPZTRGDM();
            if (pgmRtn) return;
            if (BPCRRGDM.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B020_02_CHECK_VALID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRRGND);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        BPCRRGDM.INFO.FUNC = 'C';
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
        if (BPCRRGDM.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_02_CHECK_VALID() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGTP);
        IBS.init(SCCGWA, BPCPRMR);
        BPRPRGTP.KEY.TYP = "RGNTP";
        BPRPRGTP.KEY.CD = BPCSRGND.RGN_TYPE;
        BPCPRMR.DAT_PTR = BPRPRGTP;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCTBRGN);
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = BPCSRGND.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
        CEP.TRC(SCCGWA, "DOUBLE FLAG:");
        CEP.TRC(SCCGWA, BPRPRGTP.DATA_TXT.RPT_ORG_FLG);
        if (BPRPRGTP.DATA_TXT.RPT_ORG_FLG == 'Y') {
            BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
        }
        BPCTBRGN.FUNC = 'S';
        BPCTBRGN.INFO.POINTER = BPRRGND;
        BPCTBRGN.DATA_LEN = 184;
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
        while (BPCTBRGN.RETURN_INFO != 'N') {
            BPCTBRGN.FUNC = 'R';
            S000_CALL_BPZTBRGN();
            if (pgmRtn) return;
            if (BPCTBRGN.RETURN_INFO == 'F') {
                CEP.TRC(SCCGWA, BPCSRGND.RGN_SEQ);
                CEP.TRC(SCCGWA, BPRRGND.KEY.RGN_UNIT);
                CEP.TRC(SCCGWA, BPCSRGND.UNIT);
                if (BPCSRGND.UNIT.equalsIgnoreCase(BPRRGND.KEY.RGN_UNIT)) {
                    if (BPCSRGND.RGN_SEQ == BPRRGND.KEY.RGN_NO 
                        && BPCSRGND.FUNC == 'A' 
                        || BPCSRGND.RGN_SEQ != BPRRGND.KEY.RGN_NO 
                        && BPCSRGND.FUNC == 'M') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COMPATIBLE_CONFLICT;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (BPRPRGTP.DATA_TXT.UNIT_TYPE == '0') {
                        IBS.init(SCCGWA, BPCPRGST);
                        BPCPRGST.BNK = BPCSRGND.BNK;
                        if (BPCSRGND.UNIT == null) BPCSRGND.UNIT = "";
                        JIBS_tmp_int = BPCSRGND.UNIT.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) BPCSRGND.UNIT += " ";
                        if (BPCSRGND.UNIT.substring(0, 6).trim().length() == 0) BPCPRGST.BR1 = 0;
                        else BPCPRGST.BR1 = Integer.parseInt(BPCSRGND.UNIT.substring(0, 6));
                        if (BPRRGND.KEY.RGN_UNIT == null) BPRRGND.KEY.RGN_UNIT = "";
                        JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
                        for (int i=0;i<20-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT += " ";
                        if (BPRRGND.KEY.RGN_UNIT.substring(0, 6).trim().length() == 0) BPCPRGST.BR2 = 0;
                        else BPCPRGST.BR2 = Integer.parseInt(BPRRGND.KEY.RGN_UNIT.substring(0, 6));
                        CEP.TRC(SCCGWA, BPCPRGST.BR1);
                        CEP.TRC(SCCGWA, BPCPRGST.BR2);
                        S000_CALL_BPZPRGST();
                        if (pgmRtn) return;
                        if (BPCSRGND.FLG == '0' 
                            && (BPRRGND.FLG == '2' 
                            && BPCPRGST.FLAG == 'Y' 
                            && BPCPRGST.LVL_RELATION == 'C' 
                            || BPRRGND.FLG == '1' 
                            && BPCPRGST.FLAG == 'Y' 
                            && BPCPRGST.LVL_RELATION == 'A') 
                            || BPCSRGND.FLG == '1' 
                            && ((BPRRGND.FLG == '2' 
                            || BPRRGND.FLG == '0') 
                            && BPCPRGST.FLAG == 'Y' 
                            && BPCPRGST.LVL_RELATION == 'C' 
                            || BPRRGND.FLG == '1' 
                            && BPCPRGST.FLAG == 'Y' 
                            && (BPCPRGST.LVL_RELATION == 'A' 
                            || BPCPRGST.LVL_RELATION == 'C')) 
                            || BPCSRGND.FLG == '2' 
                            && (BPRRGND.FLG == '2' 
                            && BPCPRGST.FLAG == 'Y' 
                            && (BPCPRGST.LVL_RELATION == 'A' 
                            || BPCPRGST.LVL_RELATION == 'C') 
                            || (BPRRGND.FLG == '1' 
                            || BPRRGND.FLG == '0') 
                            && BPCPRGST.FLAG == 'Y' 
                            && BPCPRGST.LVL_RELATION == 'A')) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_COMPATIBLE_CONFLICT;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
        BPCTBRGN.FUNC = 'E';
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'S';
        BPRPARM.KEY.TYPE = "RGNCD";
        WS_CODE.WS_BNK = BPCSRGND.BNK;
        WS_CODE.WS_RGN_TYPE = BPCSRGND.RGN_TYPE;
        WS_CODE.WS_RGN_SEQ = BPCSRGND.RGN_SEQ;
        BPRPARM.KEY.CODE = IBS.CLS2CPY(SCCGWA, WS_CODE);
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSRGND.EXP_DT);
        CEP.TRC(SCCGWA, BPRPARM.EXP_DATE);
        if (BPCSRGND.EXP_DT > BPRPARM.EXP_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B020_02_CHECK_VALID();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRRGND);
        BPCRRGDM.INFO.FUNC = 'R';
        BPRRGND.KEY.BNK = BPCSRGND.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
        BPRRGND.KEY.RGN_UNIT = BPCSRGND.UNIT;
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
        if (BPCRRGDM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRRGND, BPRORGND);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRRGDM.INFO.FUNC = 'U';
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRORGND;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRRGND;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        BPCRRGDM.INFO.FUNC = 'R';
        BPRRGND.KEY.BNK = BPCSRGND.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
        BPRRGND.KEY.RGN_UNIT = BPCSRGND.UNIT;
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
        if (BPCRRGDM.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRRGDM.INFO.FUNC = 'D';
        BPCRRGDM.INFO.POINTER = BPRRGND;
        BPCRRGDM.DATA_LEN = 184;
        S000_CALL_BPZTRGDM();
        if (pgmRtn) return;
    }
    public void B060_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B040_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSRGND.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCORGDO;
        SCCFMT.DATA_LEN = 185;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTBRGN);
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = BPCSRGND.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
        BPRRGND.KEY.RGN_UNIT = BPCSRGND.UNIT;
        BPCTBRGN.FUNC = 'S';
        BPCTBRGN.INFO.POINTER = BPRRGND;
        BPCTBRGN.DATA_LEN = 184;
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
        B050_01_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTBRGN.RETURN_INFO != 'N' 
            && WS_CNT <= 500 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            BPCTBRGN.FUNC = 'R';
            S000_CALL_BPZTBRGN();
            if (pgmRtn) return;
            if (BPCTBRGN.RETURN_INFO == 'F') {
                B050_03_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
        }
        BPCTBRGN.FUNC = 'E';
        S000_CALL_BPZTBRGN();
        if (pgmRtn) return;
    }
    public void B050_01_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 185;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUTPUT_HEADLINE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_RGND_HEAD);
        WS_RGND_HEAD.WS_RGND_BANK = BPCSRGND.BNK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RGND_HEAD);
        SCCMPAG.DATA_LEN = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_03_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_RGND_DETAIL);
        WS_RGND_DETAIL.WS_RGND_RGN_TYPE = BPRRGND.KEY.RGN_TYPE;
        WS_RGND_DETAIL.WS_RGND_RGN_SEQ = BPRRGND.KEY.RGN_NO;
        WS_RGND_DETAIL.WS_RGND_UNIT = BPRRGND.KEY.RGN_UNIT;
        WS_RGND_DETAIL.WS_RGND_FLG = BPRRGND.FLG;
        WS_RGND_DETAIL.WS_RGND_RMK = BPRRGND.RMK;
        WS_RGND_DETAIL.WS_RGND_EFF_DT = BPRRGND.EFF_DT;
        WS_RGND_DETAIL.WS_RGND_EXP_DT = BPRRGND.EXP_DT;
        WS_RGND_DETAIL.WS_RGND_UPT_DT = BPRRGND.UPT_DT;
        WS_RGND_DETAIL.WS_RGND_UPT_TLR = BPRRGND.UPT_TLR;
        WS_RGND_DETAIL.WS_RGND_BK = BPRRGND.KEY.BNK;
        CEP.TRC(SCCGWA, WS_RGND_DETAIL);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_RGND_DETAIL);
        SCCMPAG.DATA_LEN = 185;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_RGND_DETAIL);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRRGND.KEY.BNK = BPCSRGND.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCSRGND.RGN_TYPE;
        BPRRGND.KEY.RGN_NO = BPCSRGND.RGN_SEQ;
        BPRRGND.KEY.RGN_UNIT = BPCSRGND.UNIT;
        BPRRGND.FLG = BPCSRGND.FLG;
        BPRRGND.RMK = BPCSRGND.RMK;
        BPRRGND.EFF_DT = BPCSRGND.EFF_DT;
        BPRRGND.EXP_DT = BPCSRGND.EXP_DT;
        BPRRGND.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRRGND.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R000_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCORGDO.BNK = BPRRGND.KEY.BNK;
        BPCORGDO.RGN_TYPE = BPRRGND.KEY.RGN_TYPE;
        BPCORGDO.RGN_SEQ = BPRRGND.KEY.RGN_NO;
        BPCORGDO.UNIT = BPRRGND.KEY.RGN_UNIT;
        BPCORGDO.FLG = BPRRGND.FLG;
        BPCORGDO.RMK = BPRRGND.RMK;
        BPCORGDO.EFF_DT = BPRRGND.EFF_DT;
        BPCORGDO.EXP_DT = BPRRGND.EXP_DT;
        BPCORGDO.UPT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCORGDO.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTRGDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_RD_MT, BPCRRGDM);
        if (BPCRRGDM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRRGDM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTBRGN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_RGND, BPCTBRGN);
        if (BPCTBRGN.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTBRGN.RC);
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
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_READ, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_TYP_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRGST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG_STATION, BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
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
