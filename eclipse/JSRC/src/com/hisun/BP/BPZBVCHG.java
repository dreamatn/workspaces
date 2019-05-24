package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBVCHG {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    String R_FEE_BPZPRMM = "BP-PARM-MAINTAIN    ";
    String R_FEE_BPZPRMB = "BP-PARM-BROWSE      ";
    String S_GET_BPZSGSEQ = "BP-S-GET-SEQ";
    String PARM_FEEUC = "FEEUC";
    BPZBVCHG_WS_VARIABLES WS_VARIABLES = new BPZBVCHG_WS_VARIABLES();
    BPZBVCHG_WS_EEUC_DATA_TEMP WS_EEUC_DATA_TEMP = new BPZBVCHG_WS_EEUC_DATA_TEMP();
    BPZBVCHG_WS_COND_FLG WS_COND_FLG = new BPZBVCHG_WS_COND_FLG();
    BPCMSG_ERROR_MSG ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCGWA SCCGWA;
    BPCFEEUC BPCFEEUC;
    BPCBVCHG BPCBVCHG;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCBVCHG BPCBVCHG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBVCHG = BPCBVCHG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZBVCHG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCFEEUC = (BPCFEEUC) BPCBVCHG.INFO.POINTER;
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, WS_EEUC_DATA_TEMP);
        IBS.init(SCCGWA, BPCSGSEQ);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCBVCHG.INFO.FUNC == '0') {
            B010_ADD_PROCESS();
        } else if (BPCBVCHG.INFO.FUNC == '1') {
            B020_UPDATE_PROCESS();
        } else if (BPCBVCHG.INFO.FUNC == '2') {
            B030_DELETE_PROCESS();
        } else if (BPCBVCHG.INFO.FUNC == '3') {
            B040_QUERY_PROCESS();
        } else if (BPCBVCHG.INFO.FUNC == '4') {
            B050_READUPD_PROCESS();
        } else if (BPCBVCHG.INFO.FUNC == '5') {
            B060_BROWSE_PROCESS();
        } else {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFEEUC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_EEUC_DATA_TEMP);
        CEP.TRC(SCCGWA, BPCFEEUC.KEY.FREE_FMT_KEY);
        CEP.TRC(SCCGWA, BPCFEEUC.TXT_DATA.TXT);
        if (BPCFEEUC.TXT_DATA.TXT.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.BP_MUST_INPUT, BPCBVCHG.RC);
        } else {
            if (BPCFEEUC.KEY.FREE_FMT_KEY.trim().length() > 0) {
                B040_QUERY_PROCESS();
                if (BPCBVCHG.RETURN_INFO == 'N') {
                    B010_01_ADD_PROCESS();
                }
            } else {
                B060_01_BROWSE_PROCESS();
                if (WS_COND_FLG.TBL_FARM_FLAG == 'T') {
                    IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
                } else {
                    B010_01_ADD_PROCESS();
                }
            }
        }
    }
    public void B010_01_ADD_PROCESS() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_EEUC_DATA_TEMP);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFEEUC);
        B080_GET_SEQ_PROCESS();
        B070_INPUT_DATA_PROCESS();
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '0';
        S000_CALL_BPZPRMM();
        if (BPCBVCHG.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
        }
    }
    public void B020_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        B070_INPUT_DATA_PROCESS();
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '2';
        S000_CALL_BPZPRMM();
        IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
    }
    public void B030_DELETE_PROCESS() throws IOException,SQLException,Exception {
        B070_INPUT_DATA_PROCESS();
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '1';
        S000_CALL_BPZPRMM();
        IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        B070_INPUT_DATA_PROCESS();
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '3';
        S000_CALL_BPZPRMM();
        if (BPCBVCHG.RETURN_INFO == 'F') {
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
        }
    }
    public void B050_READUPD_PROCESS() throws IOException,SQLException,Exception {
        B070_INPUT_DATA_PROCESS();
        BPCPRMM.DAT_PTR = BPRPRMT;
        BPCPRMM.FUNC = '4';
        S000_CALL_BPZPRMM();
    }
    public void B060_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        B070_INPUT_DATA_PROCESS();
        BPCPRMB.DAT_PTR = BPRPRMT;
        if (BPCBVCHG.INFO.OPT == '0') {
            B060_01_STARTBR_PROGRAM();
        } else if (BPCBVCHG.INFO.OPT == '1') {
            B060_01_READNEXT_PROGRAM();
        } else if (BPCBVCHG.INFO.OPT == '2') {
            B060_01_ENDBR_PROGRAM();
        } else {
        }
        IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
    }
    public void B060_01_STARTBR_PROGRAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB);
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
    }
    public void B060_01_READNEXT_PROGRAM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
    }
    public void B060_01_ENDBR_PROGRAM() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
    }
    public void B060_01_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = PARM_FEEUC;
        BPRPRMT.KEY.CD = BPCFEEUC.KEY.FREE_FMT_KEY;
        BPRPRMT.DESC = BPCFEEUC.TXT_DATA.TXT;
        BPCPRMB.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRPRMT.DAT_TXT.FILLER = " ";
        BPCPRMB.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, BPRPRMT);
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        CEP.TRC(SCCGWA, BPCPRMB.RC);
        while ((BPCBVCHG.RETURN_INFO != 'N' 
            && WS_COND_FLG.TBL_FARM_FLAG != 'T')) {
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            IBS.init(SCCGWA, BPCFEEUC);
            IBS.CPY2CLS(SCCGWA, BPRPRMT.DAT_TXT.FILLER, BPCFEEUC);
            if ((WS_EEUC_DATA_TEMP.WS_TXT_DATA.TXT.equalsIgnoreCase(BPCFEEUC.TXT_DATA.TXT))) {
                WS_COND_FLG.TBL_FARM_FLAG = 'T';
            }
        }
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
    }
    public void B070_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, BPCFEEUC.KEY.FREE_FMT_KEY);
        CEP.TRC(SCCGWA, BPCFEEUC.TXT_DATA.TXT);
        BPRPRMT.KEY.TYP = PARM_FEEUC;
        BPRPRMT.KEY.CD = BPCFEEUC.KEY.FREE_FMT_KEY;
        BPRPRMT.DESC = BPCFEEUC.TXT_DATA.TXT;
        BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPCFEEUC);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
    }
    public void B080_GET_SEQ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "FEEFMT";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S010_CALL_BPZSGSEQ();
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_NORMAL)) {
            JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            BPCFEEUC.KEY.FREE_FMT_KEY = JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1);
        }
    }
    public void S010_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, S_GET_BPZSGSEQ, BPCSGSEQ);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        if ("99991221".trim().length() == 0) BPCPRMM.EXP_DT = 0;
        else BPCPRMM.EXP_DT = Integer.parseInt("99991221");
        IBS.CALLCPN(SCCGWA, R_FEE_BPZPRMM, BPCPRMM);
        T000_CHECK_RETURNCODE();
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, R_FEE_BPZPRMB, BPCPRMB);
        T000_CHECK_RETURNCODE_BROWSE();
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
    }
    public void T000_CHECK_RETURNCODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_NORMAL)) {
            BPCBVCHG.RETURN_INFO = 'F';
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_NOTFND)) {
                BPCBVCHG.RETURN_INFO = 'N';
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
                if (JIBS_tmp_str[0].equalsIgnoreCase(ERROR_MSG.BP_PARM_ALEADY_EXIST)) {
                    BPCBVCHG.RETURN_INFO = 'D';
                }
            }
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCBVCHG.RC);
    }
    public void T000_CHECK_RETURNCODE_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMB.RC);
        if (BPCPRMB.RC.RC_RTNCODE == 0) {
            BPCBVCHG.RETURN_INFO = 'F';
        } else {
            BPCBVCHG.RETURN_INFO = 'N';
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBVCHG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCBVCHG = ");
            CEP.TRC(SCCGWA, BPCBVCHG);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
