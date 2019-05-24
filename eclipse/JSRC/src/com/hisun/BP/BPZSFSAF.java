package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFSAF {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_X = "BPX01";
    String K_TBL_TXIF = "BPTFAGOR";
    String K_HIS_COPYBOOK = "BPRFAGOR";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "TXN INFOMATION MAINTAIN";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String CPN_R_FSAF = "BP-R-MGM-FSAF";
    String CPN_CI_CIZACCU_CN = "CI-INQ-ACCU";
    String CPN_CI_CIZCUST_CN = "CI-INQ-CUST";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRFAGOR BPRFAGOR = new BPRFAGOR();
    BPRFAGOR BPROAGOR = new BPRFAGOR();
    BPCRFSAF BPCRFSAF = new BPCRFSAF();
    BPCOFSA1 BPCOFSA1 = new BPCOFSA1();
    BPCOFSAL BPCOFSAL = new BPCOFSAL();
    BPCRCHNL BPCRCHNL = new BPCRCHNL();
    CICCUST CICCUST = new CICCUST();
    CICACCU CICACCU = new CICACCU();
    SMCSQWFW SMCSQWFW = new SMCSQWFW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRFBAS BPRFBAS = new BPRFBAS();
    BPCTFBAS BPCTFBAS = new BPCTFBAS();
    String WS_ERR_MSG = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSFSAF BPCSFSAF;
    public void MP(SCCGWA SCCGWA, BPCSFSAF BPCSFSAF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSFSAF = BPCSFSAF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFSAF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRFAGOR);
        IBS.init(SCCGWA, BPROAGOR);
        IBS.init(SCCGWA, BPCRFSAF);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSFSAF.FUNC);
        if (BPCSFSAF.FUNC == 'I') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSAF.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSAF.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSAF.FUNC == 'D') {
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSFSAF.FUNC == 'Q') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = BPCSFSAF.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST_CN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = BPCSFSAF.FREB_AC;
        S000_CALL_CIZACCU_CN();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFBAS);
        IBS.init(SCCGWA, BPCTFBAS);
        BPCTFBAS.INFO.POINTER = BPRFBAS;
        BPCTFBAS.INFO.REC_LEN = 312;
        BPRFBAS.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
        BPCTFBAS.INFO.FUNC = 'Q';
        S000_CALL_BPZTFBAS();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAGOR);
        BPRFAGOR.KEY.CI_NO = BPCSFSAF.CI_NO;
        BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
        BPCRFSAF.INFO.FUNC = 'Q';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        if (BPCRFSAF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAGOR);
        IBS.init(SCCGWA, BPCRFSAF);
        BPCRFSAF.INFO.POINTER = BPRFAGOR;
        BPCRFSAF.INFO.LEN = 351;
        BPRFAGOR.KEY.FEE_CODE = BPRFBAS.KEY.FEE_CODE;
        BPCRFSAF.INFO.FUNC = '4';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        BPCRFSAF.INFO.FUNC = 'N';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFAGOR.THREE_FREB_AC);
        if (BPCRFSAF.RETURN_INFO == 'F') {
            CEP.TRC(SCCGWA, "FIND SAME FEE CODE");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRFSAF.INFO.FUNC = 'E';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRFAGOR);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRFSAF.INFO.FUNC = 'C';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        if (BPCRFSAF.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAGOR);
        BPCRFSAF.INFO.FUNC = 'R';
        BPRFAGOR.KEY.CI_NO = BPCSFSAF.CI_NO;
        BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        if (BPCRFSAF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFAGOR, BPROAGOR);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRFSAF.INFO.FUNC = 'M';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        B040_01_BRW_VIEW();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAGOR);
        BPCRFSAF.INFO.FUNC = 'R';
        BPRFAGOR.KEY.CI_NO = BPCSFSAF.CI_NO;
        BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        if (BPCRFSAF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRFAGOR, BPROAGOR);
        BPCRFSAF.INFO.FUNC = 'D';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFAGOR);
        if (BPCSFSAF.CI_NO.trim().length() == 0) {
            BPRFAGOR.KEY.CI_NO = "%%%%%%%%%%%%";
            if (BPCSFSAF.FEE_CODE.trim().length() == 0) {
                BPRFAGOR.KEY.FEE_CODE = "%%%%%%%%%%%%%%%%";
                BPCRFSAF.INFO.FUNC = '1';
            } else {
                BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
                BPCRFSAF.INFO.FUNC = '4';
            }
        } else {
            BPRFAGOR.KEY.CI_NO = BPCSFSAF.CI_NO;
            if (BPCSFSAF.FEE_CODE.trim().length() == 0) {
                BPRFAGOR.KEY.FEE_CODE = "%%%%%%%%%%%%%%%%";
                BPCRFSAF.INFO.FUNC = '3';
            } else {
                BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
                BPCRFSAF.INFO.FUNC = '2';
            }
        }
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
        B040_01_01_OUT_TITLE();
        if (pgmRtn) return;
        while (BPCRFSAF.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCRFSAF.INFO.FUNC = 'N';
            S000_CALL_BPZRFSAF();
            if (pgmRtn) return;
            if (BPCRFSAF.RETURN_INFO != 'N') {
                B040_01_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        }
        BPCRFSAF.INFO.FUNC = 'E';
        S000_CALL_BPZRFSAF();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 327;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSAL);
        BPCOFSAL.CI_NO = BPRFAGOR.KEY.CI_NO;
        BPCOFSAL.CI_CNM = BPRFAGOR.CI_CNM;
        BPCOFSAL.FEE_CODE = BPRFAGOR.KEY.FEE_CODE;
        BPCOFSAL.THREE_FREB_PER = BPRFAGOR.THREE_FREB_PER;
        BPCOFSAL.THREE_FREB_AC = BPRFAGOR.THREE_FREB_AC;
        BPCOFSAL.GL_MASTER1 = BPRFAGOR.GL_MASTER1;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOFSAL);
        SCCMPAG.DATA_LEN = 327;
        B_MPAG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOFSAL);
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSFSAF.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFAGOR;
        }
        if (BPCSFSAF.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROAGOR;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRFAGOR;
        }
        if (BPCSFSAF.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROAGOR;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSA1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        SCCFMT.DATA_PTR = BPCOFSA1;
        SCCFMT.DATA_LEN = 327;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRFAGOR.KEY.CI_NO = BPCSFSAF.CI_NO;
        BPRFAGOR.CI_CNM = BPCSFSAF.CI_CNM;
        BPRFAGOR.KEY.FEE_CODE = BPCSFSAF.FEE_CODE;
        BPRFAGOR.THREE_FREB_PER = BPCSFSAF.FREB_PER;
        BPRFAGOR.THREE_FREB_AC = BPCSFSAF.FREB_AC;
        BPRFAGOR.GL_MASTER1 = BPCSFSAF.GL_MST1;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOFSA1.CI_NO = BPRFAGOR.KEY.CI_NO;
        BPCOFSA1.CI_CNM = BPRFAGOR.CI_CNM;
        BPCOFSA1.FEE_CODE = BPRFAGOR.KEY.FEE_CODE;
        BPCOFSA1.THREE_FREB_PER = BPRFAGOR.THREE_FREB_PER;
        BPCOFSA1.THREE_FREB_AC = BPRFAGOR.THREE_FREB_AC;
        BPCOFSA1.GL_MASTER1 = BPRFAGOR.GL_MASTER1;
    }
    public void S000_CALL_BPZRFSAF() throws IOException,SQLException,Exception {
        BPCRFSAF.INFO.POINTER = BPRFAGOR;
        BPCRFSAF.INFO.LEN = 351;
        IBS.CALLCPN(SCCGWA, CPN_R_FSAF, BPCRFSAF);
        if (BPCRFSAF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFSAF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTFBAS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-T-FEE-INFO     ", BPCTFBAS);
        CEP.TRC(SCCGWA, BPCTFBAS.RC.RC_CODE);
        if (BPCTFBAS.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FEE_CODE_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZACCU_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZACCU_CN, CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST_CN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        IBS.CALLCPN(SCCGWA, CPN_CI_CIZCUST_CN, CICCUST);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
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
