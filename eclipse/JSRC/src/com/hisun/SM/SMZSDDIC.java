package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZSDDIC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_NEXT_TXN_FMT = "SMX01";
    String CPN_DDIC_MAINTAIN = "SM-R_DDIC_MAINTAIN  ";
    String CPN_DDIC_BROWSE = "SM-U_DDIC_BROWSE    ";
    String WS_TEMP_BK = " ";
    char WS_TBL_BANK_FLAG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SMCX1352 SMCX1352 = new SMCX1352();
    SMCTDICM SMCTDICM = new SMCTDICM();
    SMCTDICB SMCTDICB = new SMCTDICB();
    SMCODICO SMCODICO = new SMCODICO();
    BPRDDIC BPRDDIC = new BPRDDIC();
    SCCGWA SCCGWA;
    SMCODICM SMCODICM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, SMCODICM SMCODICM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCODICM = SMCODICM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZSDDIC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRDDIC);
        IBS.init(SCCGWA, SMCTDICM);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SMCODICM.FUNC == 'I') {
            B010_INQUIRE_PROCESS();
            if (pgmRtn) return;
            R020_TRANS_DATA_INTERFACE();
            if (pgmRtn) return;
        } else if (SMCODICM.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCODICM.FUNC == 'U') {
            B030_UPDATE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCODICM.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            R100_HISTORY_RECORD();
            if (pgmRtn) return;
        } else if (SMCODICM.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_INPUT_ERROR, SMCODICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B090_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_INQUIRE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDDIC);
        CEP.TRC(SCCGWA, SMCODICM.NAME);
        BPRDDIC.KEY.NAME = SMCODICM.NAME;
        SMCTDICM.INFO.FUNC = 'Q';
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
        if (SMCTDICM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DDIC_NOTFND, SMCODICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDDIC);
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        SMCTDICM.INFO.FUNC = 'C';
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
        if (SMCTDICM.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, SMCMSG_ERROR_MSG.SM_DDIC_EXIST, SMCODICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R100_HISTORY_RECORD() throws IOException,SQLException,Exception {
    }
    public void B030_UPDATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDDIC);
        SMCTDICM.INFO.FUNC = 'R';
        BPRDDIC.KEY.NAME = SMCODICM.NAME;
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        SMCTDICM.INFO.FUNC = 'U';
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRDDIC);
        SMCTDICM.INFO.FUNC = 'R';
        BPRDDIC.KEY.NAME = SMCODICM.NAME;
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
        SMCTDICM.INFO.FUNC = 'D';
        SMCTDICM.INFO.POINTER = BPRDDIC;
        SMCTDICM.INFO.LEN = 418;
        S000_CALL_SMZTDICM();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        S000_CALL_SMZTDICB();
        if (pgmRtn) return;
    }
    public void B090_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (SMCODICM.OUTPUT_FMT.equalsIgnoreCase(K_NEXT_TXN_FMT)) {
            if (SMCODICM.FUNC == 'B') {
            } else {
                R001_TRANS_DATA_N_OUTPUT();
                if (pgmRtn) return;
                IBS.init(SCCGWA, SCCFMT);
                SCCFMT.FMTID = SMCODICM.OUTPUT_FMT;
                SCCFMT.DATA_PTR = SMCX1352;
                SCCFMT.DATA_LEN = 351;
                IBS.FMT(SCCGWA, SCCFMT);
            }
        } else {
            R010_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = SMCODICM.OUTPUT_FMT;
            SCCFMT.DATA_PTR = SMCODICO;
            SCCFMT.DATA_LEN = 352;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRDDIC.KEY.NAME = SMCODICM.NAME;
        BPRDDIC.CLASSIFY_CODE = SMCODICM.CL_CODE;
        BPRDDIC.ALIAS = SMCODICM.ALIAS;
        BPRDDIC.EN_NAME = SMCODICM.EN_NAME;
        BPRDDIC.CH_NAME = SMCODICM.CH_NAME;
        BPRDDIC.TYPE = SMCODICM.TYPE;
        BPRDDIC.LEN = SMCODICM.LEN;
        BPRDDIC.DEC_PNT = SMCODICM.DEC_PNT;
        if (SMCODICM.SIGN == 'Y') {
            BPRDDIC.SIGN = 'S';
        } else {
            BPRDDIC.SIGN = ' ';
        }
        BPRDDIC.APPL_NAME = SMCODICM.AP_NAME;
        BPRDDIC.BSN_DESC = SMCODICM.BSN_DESC;
        BPRDDIC.CRE_DATE = SMCODICM.UPD_DATE;
        BPRDDIC.CRE_ID = SMCODICM.UPD_TLR;
    }
    public void R001_TRANS_DATA_N_OUTPUT() throws IOException,SQLException,Exception {
        SMCX1352.NAME = BPRDDIC.KEY.NAME;
        SMCX1352.CL_CODE = BPRDDIC.CLASSIFY_CODE;
        SMCX1352.ALIAS = BPRDDIC.ALIAS;
        SMCX1352.EN_NAME = BPRDDIC.EN_NAME;
        SMCX1352.CH_NAME = BPRDDIC.CH_NAME;
        SMCX1352.TYPE = BPRDDIC.TYPE;
        SMCX1352.LEN = BPRDDIC.LEN;
        SMCX1352.DEC_PNT = BPRDDIC.DEC_PNT;
        if (BPRDDIC.SIGN == 'S') {
            SMCX1352.SIGN = 'Y';
        } else {
            SMCX1352.SIGN = 'N';
        }
        SMCX1352.AP_NAME = BPRDDIC.APPL_NAME;
        SMCX1352.BSN_DESC = BPRDDIC.BSN_DESC;
        SMCX1352.UPD_DATE = BPRDDIC.CRE_DATE;
        SMCX1352.UPD_TLR = BPRDDIC.CRE_ID;
    }
    public void R010_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        SMCODICO.FUNC = SMCODICM.FUNC;
        SMCODICO.NAME = BPRDDIC.KEY.NAME;
        SMCODICO.CL_CODE = BPRDDIC.CLASSIFY_CODE;
        SMCODICO.ALIAS = BPRDDIC.ALIAS;
        SMCODICO.EN_NAME = BPRDDIC.EN_NAME;
        SMCODICO.CH_NAME = BPRDDIC.CH_NAME;
        SMCODICO.TYPE = BPRDDIC.TYPE;
        SMCODICO.LEN = BPRDDIC.LEN;
        SMCODICO.DEC_PNT = BPRDDIC.DEC_PNT;
        if (BPRDDIC.SIGN == 'S') {
            SMCODICO.SIGN = 'Y';
        } else {
            SMCODICO.SIGN = 'N';
        }
        SMCODICO.AP_NAME = BPRDDIC.APPL_NAME;
        SMCODICO.BSN_DESC = BPRDDIC.BSN_DESC;
        SMCODICO.UPD_DATE = BPRDDIC.CRE_DATE;
        SMCODICO.UPD_TLR = BPRDDIC.CRE_ID;
    }
    public void R020_TRANS_DATA_INTERFACE() throws IOException,SQLException,Exception {
        SMCODICM.NAME = BPRDDIC.KEY.NAME;
        SMCODICM.CL_CODE = BPRDDIC.CLASSIFY_CODE;
        SMCODICM.ALIAS = BPRDDIC.ALIAS;
        SMCODICM.EN_NAME = BPRDDIC.EN_NAME;
        SMCODICM.CH_NAME = BPRDDIC.CH_NAME;
        SMCODICM.TYPE = BPRDDIC.TYPE;
        SMCODICM.LEN = BPRDDIC.LEN;
        SMCODICM.DEC_PNT = BPRDDIC.DEC_PNT;
        if (BPRDDIC.SIGN == 'S' 
            || BPRDDIC.SIGN == 'S') {
            SMCODICM.SIGN = 'Y';
        } else {
            SMCODICM.SIGN = 'N';
        }
        SMCODICM.AP_NAME = BPRDDIC.APPL_NAME;
        SMCODICM.BSN_DESC = BPRDDIC.BSN_DESC;
        SMCODICM.UPD_DATE = BPRDDIC.CRE_DATE;
        SMCODICM.UPD_TLR = BPRDDIC.CRE_ID;
    }
    public void S000_CALL_SMZTDICM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_MAINTAIN, SMCTDICM);
        if (SMCTDICM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SMCTDICM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCODICM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SMZTDICB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DDIC_BROWSE, SMCODICM);
        if (SMCODICM.RC.RC_CODE != 0) {
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
