package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPACCR {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String BP_P_QUERY_BKAI = "BP-P-QUERY-ACCR";
    String WS_ERR_MSG = " ";
    short WS_MONTH = 0;
    short WS_MONTH_IACCR = 0;
    short WS_ACCR_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQBAI BPCPQBAI = new BPCPQBAI();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPACCR BPCPACCR;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCPACCR BPCPACCR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPACCR = BPCPACCR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZPACCR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_GET_ACCR();
        B300_GET_ISACCR();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPACCR.DATA.BNK.equalsIgnoreCase("0")) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BNK_MUST_INPUT, BPCPACCR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_GET_ACCR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBAI);
        BPCPQBAI.DATA_INFO.BNK = BPCPACCR.DATA.BNK;
        S000_CALL_BPZPQBAI();
    }
    public void B300_GET_ISACCR() throws IOException,SQLException,Exception {
        BPCPACCR.DATA.ISACCR = 'N';
        CEP.TRC(SCCGWA, "1234");
        CEP.TRC(SCCGWA, BPCPACCR.DATA.ISACCR);
        CEP.TRC(SCCGWA, BPCPQBAI.DATA_INFO.ACCR_CYCLE);
        if (BPCPQBAI.DATA_INFO.ACCR_CYCLE == '0') {
            JIBS_tmp_str[0] = "" + BPCPACCR.DATA.DATE;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if ((BPCPQBAI.DATA_INFO.ACCR_DAY == Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 4 - 1)))) {
                BPCPACCR.DATA.ISACCR = 'Y';
            }
        } else if (BPCPQBAI.DATA_INFO.ACCR_CYCLE == '1') {
            JIBS_tmp_str[0] = "" + BPCPQBAI.DATA_INFO.ACCR_CNT;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_ACCR_CNT = 0;
            else WS_ACCR_CNT = Short.parseShort(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
            JIBS_tmp_str[0] = "" + BPCPQBAI.DATA_INFO.ACCR_DAY;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            JIBS_tmp_str[1] = "" + BPCPACCR.DATA.DATE;
            JIBS_tmp_int = JIBS_tmp_str[1].length();
            for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[1] = "0"+JIBS_tmp_str[1];
            if ((JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).equalsIgnoreCase(JIBS_tmp_str[1].substring(7 - 1, 7 + 2 - 1)))) {
                if (WS_ACCR_CNT == 3) {
                    JIBS_tmp_str[0] = "" + BPCPACCR.DATA.DATE;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if ((JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("3") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("6") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("9") 
                        || JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).equalsIgnoreCase("12"))) {
                        BPCPACCR.DATA.ISACCR = 'Y';
                    }
                } else {
                    BPCPACCR.DATA.ISACCR = 'Y';
                }
            }
        } else if (BPCPQBAI.DATA_INFO.ACCR_CYCLE == '2') {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
            BPCOCLWD.DAYE_FLG = 'Y';
            BPCOCLWD.DATE1 = BPCPACCR.DATA.DATE;
            S000_CALL_BPZOCLWD();
            JIBS_tmp_str[0] = "" + BPCPQBAI.DATA_INFO.ACCR_DAY;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<4-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            if (BPCOCLWD.WEEK_NO1 == Short.parseShort(JIBS_tmp_str[0].substring(4 - 1, 4 + 1 - 1))) {
                BPCPACCR.DATA.ISACCR = 'Y';
            }
        } else if (BPCPQBAI.DATA_INFO.ACCR_CYCLE == '3') {
            BPCPACCR.DATA.ISACCR = 'Y';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID CYCLE";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, "2222");
    }
    public void S000_CALL_BPZPQBAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BKAI", BPCPQBAI);
        if (BPCPQBAI.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCPQBAI.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZOCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = "" + BPCOCLWD.RC.RC_CODE;
            JIBS_tmp_int = WS_ERR_MSG.length();
            for (int i=0;i<4-JIBS_tmp_int;i++) WS_ERR_MSG = "0" + WS_ERR_MSG;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPACCR.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPACCR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPACCR = ");
            CEP.TRC(SCCGWA, BPCPACCR);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
