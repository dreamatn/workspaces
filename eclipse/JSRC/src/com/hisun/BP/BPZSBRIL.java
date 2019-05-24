package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBRIL {
    String K_HIS_REMARKS = "BOXLIB INFOMATION MAINTAIN";
    String K_CPY_BPRBRIS = "BPRBRIS";
    String CPN_BR_RIS_BRW = "BP-BR-RIS-BRW";
    String CPN_R_ADW_BRIS = "BP-R-ADW-BRIS";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_BR = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTBRIS BPCTBRIS = new BPCTBRIS();
    BPCOBRIO BPCOBRIO = new BPCOBRIO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSBRIB BPCSBRIB = new BPCSBRIB();
    BPRBRIS BPRBRIS = new BPRBRIS();
    BPRBRIS BPRORIS = new BPRBRIS();
    SCCGWA SCCGWA;
    BPCSBRIL BPCSBRIL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBRIL BPCSBRIL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBRIL = BPCSBRIL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSBRIL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCTBRIS);
        IBS.init(SCCGWA, BPCSBRIL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBRIL.FUNC);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPCSBRIL.FUNC == 'A') {
                B010_ADD_PROCESS_FOR_CN();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'D') {
                B040_QUERY_PROCESS_FOR_CN();
                B020_DELETE_PROCESS_FOR_CN();
                B020_01_HISTORY_RECORD();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'M') {
                B030_MODIFY_PROCESS_FOR_CN();
                B030_01_HISTORY_RECORD();
                R010_TRANS_DATA_OUPUT();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'I') {
                B040_QUERY_PROCESS_FOR_CN();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'B') {
                B050_QUERY_DATA_PROCESS();
            } else {
                CEP.TRC(SCCGWA, "ERR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (BPCSBRIL.FUNC == 'A') {
                B010_ADD_PROCESS();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'D') {
                B040_QUERY_PROCESS();
                B020_DELETE_PROCESS();
                B020_01_HISTORY_RECORD();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'M') {
                B030_MODIFY_PROCESS();
                B030_01_HISTORY_RECORD();
                R010_TRANS_DATA_OUPUT();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'I') {
                B040_QUERY_PROCESS();
                R000_TRANS_DATA_OUTPUT();
            } else if (BPCSBRIL.FUNC == 'B') {
                B050_QUERY_DATA_PROCESS();
            } else {
                CEP.TRC(SCCGWA, "ERR");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B010_ADD_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        B010_CHECK_BR_EXIST_FOR_CN();
        R000_TRANS_DATA_PARAMETER();
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'A';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTBRIS.RC);
            S000_ERR_MSG_PROC();
        }
        B010_01_HISTORY_RECORD();
        R010_TRANS_DATA_OUPUT();
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        B010_CHECK_BR_EXIST();
        R000_TRANS_DATA_PARAMETER();
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'A';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTBRIS.RC);
            S000_ERR_MSG_PROC();
        }
        B010_01_HISTORY_RECORD();
        R010_TRANS_DATA_OUPUT();
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBRIS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B010_CHECK_BR_EXIST() throws IOException,SQLException,Exception {
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_BRIS_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_BR_EXIST_FOR_CN() throws IOException,SQLException,Exception {
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_BRIS_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_DELETE_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'R';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCTBRIS.INFO.FUNC = 'D';
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'D';
        S000_CALL_BPZTBRIS();
        R010_TRANS_DATA_OUPUT();
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'R';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        BPCTBRIS.INFO.FUNC = 'D';
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'D';
        S000_CALL_BPZTBRIS();
        R010_TRANS_DATA_OUPUT();
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBRIS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B020_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBRIS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
    }
    public void B030_MODIFY_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'R';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.CLONE(SCCGWA, BPRBRIS, BPRORIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.INSR_CCY = BPCSBRIL.INSR_CCY;
        BPRBRIS.BXIR_AMT = BPCSBRIL.BOIS_AMT;
        BPRBRIS.PLIR_AMT = BPCSBRIL.PBIS_AMT;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPRBRIS.LMT_U = BPCSBRIL.MAX_LMT;
        BPRBRIS.LMT_L = BPCSBRIL.MIN_LMT;
        BPRBRIS.LAST_DT = BPCSBRIL.UPD_DT;
        BPRBRIS.UPD_TLR = BPCSBRIL.UPD_TLR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'U';
        S000_CALL_BPZTBRIS();
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'R';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        IBS.CLONE(SCCGWA, BPRBRIS, BPRORIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.INSR_CCY = BPCSBRIL.INSR_CCY;
        BPRBRIS.BXIR_AMT = BPCSBRIL.BOIS_AMT;
        BPRBRIS.PLIR_AMT = BPCSBRIL.PBIS_AMT;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPRBRIS.LMT_U = BPCSBRIL.MAX_LMT;
        BPRBRIS.LMT_L = BPCSBRIL.MIN_LMT;
        BPRBRIS.LAST_DT = BPCSBRIL.UPD_DT;
        BPRBRIS.UPD_TLR = BPCSBRIL.UPD_TLR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'U';
        S000_CALL_BPZTBRIS();
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HISTORYSTART");
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBRIS;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRORIS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRBRIS;
        S000_CALL_BPZPNHIS();
        CEP.TRC(SCCGWA, "HISTORYEND");
    }
    public void B040_QUERY_PROCESS_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        R010_TRANS_DATA_OUPUT();
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBRIS);
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPCTBRIS.POINTER = BPRBRIS;
        BPCTBRIS.LEN = 134;
        BPCTBRIS.INFO.FUNC = 'Q';
        S000_CALL_BPZTBRIS();
        if (BPCTBRIS.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BRIS_NOTFND;
            S000_ERR_MSG_PROC();
        }
        R010_TRANS_DATA_OUPUT();
    }
    public void B050_QUERY_DATA_PROCESS() throws IOException,SQLException,Exception {
        BPCSBRIB.BR = BPCSBRIL.BR;
        S000_CALL_BPZSBRIB();
    }
    public void S000_CALL_BPZSBRIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BR_RIS_BRW, BPCSBRIB);
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBRIL.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBRIO;
        SCCFMT.DATA_LEN = 79;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOBRIO);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRBRIS.KEY.BR = BPCSBRIL.BR;
        BPRBRIS.INSR_CCY = BPCSBRIL.INSR_CCY;
        BPRBRIS.BXIR_AMT = BPCSBRIL.BOIS_AMT;
        BPRBRIS.PLIR_AMT = BPCSBRIL.PBIS_AMT;
        BPRBRIS.KEY.LMT_CCY = BPCSBRIL.LMT_CCY;
        BPRBRIS.LMT_U = BPCSBRIL.MAX_LMT;
        BPRBRIS.LMT_L = BPCSBRIL.MIN_LMT;
        BPRBRIS.OPEN_DT = BPCSBRIL.UPD_DT;
        BPRBRIS.OPEN_TLR = BPCSBRIL.UPD_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOBRIO.FUNC = BPCSBRIL.FUNC;
        BPCOBRIO.BR = BPRBRIS.KEY.BR;
        BPCOBRIO.INSR_CCY = BPRBRIS.INSR_CCY;
        BPCOBRIO.BXIR_AMT = BPRBRIS.BXIR_AMT;
        BPCOBRIO.PLIR_AMT = BPRBRIS.PLIR_AMT;
        BPCOBRIO.LMT_CCY = BPRBRIS.KEY.LMT_CCY;
        BPCOBRIO.LMT_U = BPRBRIS.LMT_U;
        BPCOBRIO.LMT_L = BPRBRIS.LMT_L;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZTBRIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BRIS, BPCTBRIS);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
