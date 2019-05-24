package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCNTG {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN";
    String K_CPN_R_BPZRMBPM = "BP-R-MBRW-PARM";
    String K_HIS_COPYBOOK_NAME = "BPCHPCD";
    String K_PARM_TYPE = "CNTYG";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP741";
    int WS_INIT_EXP_DATE = 99991221;
    String K_HIS_REMARKS = "COUNTRY GROUP MAINTENANCE";
    String WS_ERR_MSG = " ";
    char WS_OUTPUT_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRCNTYG BPRCNTYG = new BPRCNTYG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRCNTYG BPRNCNTG = new BPRCNTYG();
    BPRCNTYG BPRBCNTG = new BPRCNTYG();
    BPCOCNTG BPCOCNTG = new BPCOCNTG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSCNTG BPCSCNTG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCNTG BPCSCNTG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCNTG = BPCSCNTG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSCNTG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPCRMBPM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNTG);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCSCNTG.FUNC == 'Q') {
            B010_QUERY_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTG.FUNC == 'A') {
            B020_ADD_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTG.FUNC == 'U') {
            B030_UPDATE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTG.FUNC == 'D') {
            B040_DELETE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCNTG.FUNC == 'B') {
            B050_BROWSE_CNTY_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSCNTG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCNTG.FUNC);
        if (BPCSCNTG.FUNC == 'Q' 
            && BPCSCNTG.FUNC == 'A' 
            && BPCSCNTG.FUNC == 'U' 
            && BPCSCNTG.FUNC == 'B' 
            && BPCSCNTG.FUNC == 'D') {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSCNTG.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        if (BPCSCNTG.FUNC == 'Q' 
            || BPCSCNTG.FUNC == 'A' 
            || BPCSCNTG.FUNC == 'U' 
            || BPCSCNTG.FUNC == 'D') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
            if (JIBS_tmp_str[0].trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTY_CD_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCNTYG);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCNTYG.KEY.TYP = "CNTYG";
        BPRCNTYG.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPRMM.EFF_DT = BPCSCNTG.EFF_DATE_PARM;
        CEP.TRC(SCCGWA, BPCSCNTG.EFF_DATE_PARM);
        CEP.TRC(SCCGWA, BPRCNTYG.KEY.CD);
        BPCPRMM.FUNC = '3';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_ADD_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCNTYG);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCNTYG.KEY.TYP = "CNTYG";
        BPRCNTYG.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        CEP.TRC(SCCGWA, BPCSCNTG.KEY);
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPRMM.EXP_DT = WS_INIT_EXP_DATE;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNTG.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRCNTYG.DATA_TXT);
        BPCPRMM.FUNC = '0';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B030_UPDATE_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCNTYG);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCNTYG.KEY.TYP = "CNTYG";
        BPRCNTYG.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPRMM.EFF_DT = BPCSCNTG.EFF_DATE_PARM;
        BPCPRMM.FUNC = '4';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPCPRMM.FUNC = '2';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCNTYG);
        IBS.init(SCCGWA, BPCPRMM);
        BPRCNTYG.KEY.TYP = "CNTYG";
        BPRCNTYG.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPRMM.EFF_DT = BPCSCNTG.EFF_DATE_PARM;
        BPCPRMM.FUNC = '4';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        BPRCNTYG.KEY.TYP = "CNTYG";
        BPRCNTYG.KEY.CD = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPRMM.EFF_DT = BPCSCNTG.EFF_DATE_PARM;
        BPCPRMM.FUNC = '1';
        S010_CALL_BPZPRMM();
        if (pgmRtn) return;
        S020_HISTORY_PROCESS();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_CNTY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 149;
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_TYPE;
        BPRPARM.KEY.CODE = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'N' 
            && BPCRMBPM.RETURN_INFO != 'L' 
            && SCCMPAG.FUNC != 'E') {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
            if (JIBS_tmp_str[0].trim().length() == 0) {
                R010_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            } else {
                if (BPCSCNTG.KEY.AREA_CD.trim().length() > 0 
                    && BPCSCNTG.KEY.GROUP_CD.trim().length() > 0) {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
                    if (JIBS_tmp_str[0].equalsIgnoreCase(BPRPARM.KEY.CODE)) {
                        R010_OUTPUT_DATA_PROCESS();
                        if (pgmRtn) return;
                    }
                } else {
                    if (BPCSCNTG.KEY.AREA_CD.trim().length() > 0) {
                        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                        if (BPCSCNTG.KEY.AREA_CD.equalsIgnoreCase(BPRPARM.KEY.CODE.substring(0, 4))) {
                            R010_OUTPUT_DATA_PROCESS();
                            if (pgmRtn) return;
                        }
                    } else {
                        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                        if (BPCSCNTG.KEY.GROUP_CD.equalsIgnoreCase(BPRPARM.KEY.CODE.substring(5 - 1, 5 + 4 - 1))) {
                            R010_OUTPUT_DATA_PROCESS();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        CEP.TRC(SCCGWA, BPCRMBPM.FUNC);
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCNTG);
        if (BPRCNTYG.KEY.CD == null) BPRCNTYG.KEY.CD = "";
        JIBS_tmp_int = BPRCNTYG.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCNTYG.KEY.CD += " ";
        BPCOCNTG.AREA_CD = BPRCNTYG.KEY.CD.substring(0, 4);
        if (BPRCNTYG.KEY.CD == null) BPRCNTYG.KEY.CD = "";
        JIBS_tmp_int = BPRCNTYG.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCNTYG.KEY.CD += " ";
        BPCOCNTG.GROUP_CD = BPRCNTYG.KEY.CD.substring(5 - 1, 5 + 4 - 1);
        if (BPRCNTYG.KEY.CD == null) BPRCNTYG.KEY.CD = "";
        JIBS_tmp_int = BPRCNTYG.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRCNTYG.KEY.CD += " ";
        BPCOCNTG.CNTY_CD = BPRCNTYG.KEY.CD.substring(9 - 1, 9 + 4 - 1);
        BPCOCNTG.DATA.REMARK = BPRCNTYG.DATA_TXT.DESC;
        BPCOCNTG.DATA.EFF_DATE = BPRCNTYG.DATA_TXT.EFF_DATE;
        BPCOCNTG.DATA.EXP_DATE = BPRCNTYG.DATA_TXT.EXP_DATE;
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSCNTG.FUNC == 'Q') {
            SCCFMT.FMTID = K_FMT_CD_1;
        } else {
            SCCFMT.FMTID = K_FMT_CD_2;
        }
        SCCFMT.DATA_PTR = BPCOCNTG;
        SCCFMT.DATA_LEN = 149;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R010_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCNTG);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPCOCNTG.AREA_CD = BPRPARM.KEY.CODE.substring(0, 4);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPCOCNTG.GROUP_CD = BPRPARM.KEY.CODE.substring(5 - 1, 5 + 4 - 1);
        if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
        JIBS_tmp_int = BPRPARM.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
        BPCOCNTG.CNTY_CD = BPRPARM.KEY.CODE.substring(9 - 1, 9 + 4 - 1);
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, BPCOCNTG.DATA);
        CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
        CEP.TRC(SCCGWA, BPCOCNTG.AREA_CD);
        CEP.TRC(SCCGWA, BPCOCNTG.GROUP_CD);
        CEP.TRC(SCCGWA, BPCOCNTG.CNTY_CD);
        CEP.TRC(SCCGWA, BPCOCNTG.DATA);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOCNTG);
        SCCMPAG.DATA_LEN = 149;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S010_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPRCNTYG.DATA_LEN = 136;
        BPCPRMM.DAT_PTR = BPRCNTYG;
        IBS.CALLCPN(SCCGWA, K_CPN_PARM_MAINTAIN, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRCNTYG;
        IBS.CALLCPN(SCCGWA, K_CPN_R_BPZRMBPM, BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RETURN_INFO);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S020_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSCNTG.FUNC == 'A' 
            || BPCSCNTG.FUNC == 'D') {
            S020_01_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSCNTG.FUNC == 'U') {
            S020_02_HISTORY_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void S020_01_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCNTG.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSCNTG.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.JRNNO);
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPNHIS.INFO.TX_TYP_CD = "BPCNTY";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S020_02_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = "BPVCNTY";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.REF_NO = IBS.CLS2CPY(SCCGWA, BPCSCNTG.KEY);
        BPCPNHIS.INFO.TX_TYP_CD = "BPCNTY";
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.TX_TYP_CD);
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRBCNTG;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRNCNTG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
