package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSCGWS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP199";
    String K_OUTPUT_MPAG = "SCZ01";
    String K_R_DRW_CGWY = "BP-R-DRW-CGWY";
    String K_HIS_REMARKS = "DEVIATION MAINTENANCE";
    String K_PRDT_INF_MAINT = "BP-S-MAINT-PRDT-INFO";
    String K_HIS_COPYBOOK_NAME = "BPCHCGWY";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    BPZSCGWS_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSCGWS_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCHCGWY BPCOCGWY = new BPCHCGWY();
    BPCHCGWY BPCNCGWY = new BPCHCGWY();
    BPCRCGWY BPCRCGWY = new BPCRCGWY();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRCGWY BPRCGWY = new BPRCGWY();
    SCCGWA SCCGWA;
    BPCSCGWY BPCSCGWY;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSCGWY BPCSCGWY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSCGWY = BPCSCGWY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZSCGWS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCSCGWY.RC);
        IBS.init(SCCGWA, BPRCGWY);
        IBS.init(SCCGWA, BPCRCGWY);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 END");
        if (BPCSCGWY.FUNC == 'Q'
            || BPCSCGWY.FUNC == 'C'
            || BPCSCGWY.FUNC == 'U'
            || BPCSCGWY.FUNC == 'D') {
            B200_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSCGWY.FUNC == 'B') {
            B300_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSCGWY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSCGWY.PRDT_CODE.trim().length() == 0 
            && BPCSCGWY.FUNC != 'B') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUST_INPUT, BPCSCGWY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSCGWY.FUNC != 'B') {
            BPCRCGWY.FUNC = 'Q';
            BPRCGWY.KEY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPRCGWY.KEY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
            CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
            CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
            S000_CALL_BPZRCGWY();
            if (pgmRtn) return;
            if (BPCRCGWY.RETURN_INFO == 'N') {
                if (BPCSCGWY.FUNC == 'Q' 
                    || BPCSCGWY.FUNC == 'U' 
                    || BPCSCGWY.FUNC == 'D') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCSCGWY.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPCSCGWY.FUNC == 'C') {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_ALEADY_EXIST, BPCSCGWY.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        if (BPCSCGWY.FUNC == 'C' 
            || BPCSCGWY.FUNC == 'U') {
            IBS.init(SCCGWA, BPCSMPRD);
            BPCSMPRD.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPCSMPRD.INFO.FUNC = 'Q';
            S000_CALL_BPZSMPRD();
            if (pgmRtn) return;
            if (BPCSMPRD.PARM_CODE.trim().length() > 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_THE_PRDT_HAS_PARM_CD, BPCSCGWY.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCGWY);
        IBS.init(SCCGWA, BPRCGWY);
        if (BPCSCGWY.FUNC == 'Q') {
            BPCRCGWY.FUNC = 'Q';
        } else if (BPCSCGWY.FUNC == 'C') {
            BPCRCGWY.FUNC = 'A';
        } else if (BPCSCGWY.FUNC == 'U'
            || BPCSCGWY.FUNC == 'D') {
            BPCRCGWY.FUNC = 'M';
        }
        BPRCGWY.KEY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
        BPRCGWY.KEY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
        BPRCGWY.PARM_CODE = BPCSCGWY.PARM_CODE;
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        CEP.TRC(SCCGWA, BPCSCGWY.PARM_CODE);
        S000_CALL_BPZRCGWY();
        if (pgmRtn) return;
        R000_OLD_DAT_PROCESS();
        if (pgmRtn) return;
        if (BPCSCGWY.FUNC == 'C' 
            || BPCSCGWY.FUNC == 'U' 
            || BPCSCGWY.FUNC == 'D') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSCGWY.FUNC == 'D') {
            BPCRCGWY.FUNC = 'D';
            S000_CALL_BPZRCGWY();
            if (pgmRtn) return;
        }
        if (BPCSCGWY.FUNC == 'U') {
            BPCRCGWY.FUNC = 'U';
            BPRCGWY.KEY.PRDT_CODE = BPCOCGWY.PRDT_CODE;
            BPRCGWY.KEY.CHANG_WAY = BPCOCGWY.CHANG_WAY;
            BPRCGWY.PARM_CODE = BPCOCGWY.PARM_CODE;
            CEP.TRC(SCCGWA, BPRCGWY.KEY.PRDT_CODE);
            CEP.TRC(SCCGWA, BPRCGWY.KEY.CHANG_WAY);
            CEP.TRC(SCCGWA, BPRCGWY.PARM_CODE);
            S000_CALL_BPZRCGWY();
            if (pgmRtn) return;
        }
        R000_FMT_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B300_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B500_OUT_TITLE();
        if (pgmRtn) return;
        B400_INPUT_DATA();
        if (pgmRtn) return;
        BPCRCGWY.FUNC = 'B';
        S000_CALL_BPZRCGWY();
        if (pgmRtn) return;
        BPCRCGWY.FUNC = 'R';
        S000_CALL_BPZRCGWY();
        if (pgmRtn) return;
        while (BPCRCGWY.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            R000_MPAG_OUTPUT_DATA();
            if (pgmRtn) return;
            BPCRCGWY.FUNC = 'R';
            S000_CALL_BPZRCGWY();
            if (pgmRtn) return;
        }
        BPCRCGWY.FUNC = 'E';
        S000_CALL_BPZRCGWY();
        if (pgmRtn) return;
    }
    public void B400_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSCGWY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPCSCGWY.CHANG_WAY);
        BPRCGWY.KEY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
        BPRCGWY.KEY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
        if (BPCSCGWY.PRDT_CODE.trim().length() == 0 
                && BPCSCGWY.CHANG_WAY.trim().length() == 0) {
            BPCRCGWY.OPTION = '1';
        } else if (BPCSCGWY.PRDT_CODE.trim().length() > 0 
                && BPCSCGWY.CHANG_WAY.trim().length() == 0) {
            BPCRCGWY.OPTION = '2';
        } else if (BPCSCGWY.PRDT_CODE.trim().length() == 0 
                && BPCSCGWY.CHANG_WAY.trim().length() > 0) {
            BPCRCGWY.OPTION = '3';
        } else if (BPCSCGWY.PRDT_CODE.trim().length() > 0 
                && BPCSCGWY.CHANG_WAY.trim().length() > 0) {
            BPCRCGWY.OPTION = '4';
        }
    }
    public void B500_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 24;
        SCCMPAG.SCR_ROW_CNT = 50;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_DATA;
        SCCFMT.DATA_LEN = 24;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_MPAG_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
        SCCMPAG.DATA_LEN = 24;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCGWY.KEY.PRDT_CODE);
        CEP.TRC(SCCGWA, BPRCGWY.KEY.CHANG_WAY);
        CEP.TRC(SCCGWA, BPRCGWY.PARM_CODE);
        WS_OUTPUT_DATA.WS_PRDT_CODE = BPRCGWY.KEY.PRDT_CODE;
        WS_OUTPUT_DATA.WS_CHANG_WAY = BPRCGWY.KEY.CHANG_WAY;
        WS_OUTPUT_DATA.WS_PARM_CODE = BPRCGWY.PARM_CODE;
    }
    public void R000_OLD_DAT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSCGWY.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOCGWY);
            BPCOCGWY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPCOCGWY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
            BPCOCGWY.PARM_CODE = BPCSCGWY.PARM_CODE;
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCRCGWY.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSCGWY.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSCGWY.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.REF_NO = BPCSCGWY.PRDT_CODE;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        if (BPCSCGWY.FUNC == 'U') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOCGWY;
            IBS.init(SCCGWA, BPCNCGWY);
            BPCNCGWY.PRDT_CODE = BPCSCGWY.PRDT_CODE;
            BPCNCGWY.CHANG_WAY = BPCSCGWY.CHANG_WAY;
            BPCNCGWY.PARM_CODE = BPCSCGWY.PARM_CODE;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNCGWY;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRCGWY() throws IOException,SQLException,Exception {
        BPCRCGWY.POINTER = BPRCGWY;
        BPCRCGWY.LEN = 50;
        IBS.CALLCPN(SCCGWA, K_R_DRW_CGWY, BPCRCGWY);
        if (BPCRCGWY.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCGWY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCGWY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSCGWY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSMPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_PRDT_INF_MAINT, BPCSMPRD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSCGWY.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
