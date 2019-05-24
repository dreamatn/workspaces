package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCNGL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "CNGL DETAILS INFO ";
    String K_HIS_COPYBOOK_NAME = "BPCHCNGL";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCNGL BPRCNGL = new BPRCNGL();
    BPCRCNGL BPCRCNGL = new BPCRCNGL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHCNGL BPCHCNGL = new BPCHCNGL();
    BPCHCNGL BPCNCNGL = new BPCHCNGL();
    BPCHCNGL BPCOCNGL = new BPCHCNGL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGWA SCCGWA;
    BPCUCNGL BPCUCNGL;
    public void MP(SCCGWA SCCGWA, BPCUCNGL BPCUCNGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUCNGL = BPCUCNGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCNGL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCNGL);
        IBS.init(SCCGWA, BPRCNGL);
        IBS.init(SCCGWA, BPCNCNGL);
        IBS.init(SCCGWA, BPCOCNGL);
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUCNGL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (!(BPCUCNGL.FUNC == 'S' 
            || BPCUCNGL.FUNC == 'N' 
            || BPCUCNGL.FUNC == 'E')) {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
        }
        if (BPCUCNGL.FUNC == 'I') {
            S000_TRANS_BPRCNGL();
            if (pgmRtn) return;
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'A') {
            S000_TRANS_BPRCNGL();
            if (pgmRtn) return;
            B030_ADD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'U') {
            S000_TRANS_BPRCNGL();
            if (pgmRtn) return;
            B040_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'D') {
            S000_TRANS_BPRCNGL();
            if (pgmRtn) return;
            B050_DELETE_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'S') {
            S000_TRANS_BPRCNGL();
            if (pgmRtn) return;
            B061_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'N') {
            B062_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCUCNGL.FUNC == 'E') {
            B063_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCUCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        S000_TRANS_UCNGL_DAT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUCNGL.DAT.KEY.CNTR_TYPE.trim().length() == 0 
            || BPCUCNGL.DAT.KEY.CNTR_TYPE.charAt(0) == 0X00) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT, BPCUCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRCNGL.FUNC = 'I';
        CEP.TRC(SCCGWA, BPRCNGL.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.BR);
        CEP.TRC(SCCGWA, BPRCNGL.KEY.OTH);
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
    }
    public void B030_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNCNGL);
        BPCNCNGL.TXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPCNCNGL.TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCNCNGL.TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCNCNGL.TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCNCNGL.TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRCNGL.FUNC = 'A';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B040_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNCNGL);
        BPCNCNGL.TXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPCNCNGL.TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCNCNGL.TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCNCNGL.TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCNCNGL.TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRCNGL.FUNC = 'U';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
        S000_TRANS_UCNGL_DAT();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCNGL);
        BPRCNGL.KEY.CNTR_TYPE = BPCNCNGL.KEY.CNTR_TYPE;
        BPRCNGL.KEY.BOOK_FLG = BPCNCNGL.KEY.BOOK_FLG;
        BPRCNGL.KEY.BR = BPCNCNGL.KEY.BR;
        BPRCNGL.KEY.OTH = BPCNCNGL.KEY.OTH;
        BPRCNGL.MSTNO = BPCNCNGL.TXT.MSTNO;
        BPRCNGL.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRCNGL.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPRCNGL.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRCNGL.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRCNGL.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        if (BPCNCNGL.TXT.MSTNO == BPCOCNGL.TXT.MSTNO 
            && BPCNCNGL.KEY.BR == BPCOCNGL.KEY.BR 
            && BPCNCNGL.KEY.OTH.equalsIgnoreCase(BPCOCNGL.KEY.OTH)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATA_NCHG_ERR, BPCUCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRCNGL.FUNC = 'M';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
        B030_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B050_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUCNGL.DAT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCNGL);
        BPCOCNGL.TXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPCOCNGL.TXT.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
        BPCOCNGL.TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPCOCNGL.TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPCOCNGL.TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCRCNGL.FUNC = 'U';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
        BPCRCNGL.FUNC = 'D';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
        B020_01_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void B061_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRCNGL.FUNC = 'S';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
    }
    public void B062_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRCNGL.FUNC = 'N';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
    }
    public void B063_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCRCNGL.FUNC = 'E';
        S000_CALL_BPZRCNGL();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCUCNGL.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            IBS.init(SCCGWA, BPCOCNGL);
        } else {
            BPCPNHIS.INFO.TX_TYP = 'D';
            IBS.init(SCCGWA, BPCNCNGL);
        }
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 167;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCNCNGL;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCOCNGL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID_LEN = 167;
        BPCPNHIS.INFO.OLD_DAT_PT = BPCOCNGL;
        BPCPNHIS.INFO.NEW_DAT_PT = BPCNCNGL;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_TRANS_BPRCNGL() throws IOException,SQLException,Exception {
        BPRCNGL.KEY.CNTR_TYPE = BPCUCNGL.DAT.KEY.CNTR_TYPE;
        BPRCNGL.KEY.BOOK_FLG = BPCUCNGL.DAT.KEY.BOOK_FLG;
        if (BPCUCNGL.FUNC == 'U') {
            BPRCNGL.KEY.BR = BPCUCNGL.DAT.TXT.BR_OLD;
            BPRCNGL.KEY.OTH = BPCUCNGL.DAT.TXT.OTH_OLD;
        } else {
            BPRCNGL.KEY.BR = BPCUCNGL.DAT.KEY.BR;
            BPRCNGL.KEY.OTH = BPCUCNGL.DAT.KEY.OTH;
        }
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY.CNTR_TYPE);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY.BOOK_FLG);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY.OTH);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.KEY.BR);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.TXT.OTH_OLD);
        CEP.TRC(SCCGWA, BPCUCNGL.DAT.TXT.BR_OLD);
        if (BPCUCNGL.FUNC == 'A' 
            || BPCUCNGL.FUNC == 'U') {
            BPRCNGL.MSTNO = BPCUCNGL.DAT.TXT.MSTNO;
            BPRCNGL.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
            BPRCNGL.UPD_DATE = SCCGWA.COMM_AREA.TR_DATE;
            BPRCNGL.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            BPRCNGL.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
            BPRCNGL.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        }
    }
    public void S000_TRANS_UCNGL_DAT() throws IOException,SQLException,Exception {
        BPCUCNGL.DAT.KEY.CNTR_TYPE = BPRCNGL.KEY.CNTR_TYPE;
        BPCUCNGL.DAT.KEY.BOOK_FLG = BPRCNGL.KEY.BOOK_FLG;
        BPCUCNGL.DAT.KEY.BR = BPRCNGL.KEY.BR;
        BPCUCNGL.DAT.KEY.OTH = BPRCNGL.KEY.OTH;
        BPCUCNGL.DAT.TXT.MSTNO = BPRCNGL.MSTNO;
        BPCUCNGL.DAT.TXT.UPD_TEL = BPRCNGL.UPD_TEL;
        BPCUCNGL.DAT.TXT.UPD_DATE = BPRCNGL.UPD_DATE;
        BPCUCNGL.DAT.TXT.UPD_TIME = BPRCNGL.UPD_TIME;
        BPCUCNGL.DAT.TXT.SUP_TEL1 = BPRCNGL.SUP_TEL1;
        BPCUCNGL.DAT.TXT.SUP_TEL2 = BPRCNGL.SUP_TEL2;
    }
    public void S000_CALL_BPZRCNGL() throws IOException,SQLException,Exception {
        BPCRCNGL.DAT_PTR = BPRCNGL;
        CEP.TRC(SCCGWA, BPRCNGL.MSTNO);
        IBS.CALLCPN(SCCGWA, "BP-R-MAINT-CNGL", BPCRCNGL);
        if (BPCRCNGL.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGL.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CNTR_NOT_EXIST) 
                && (BPCUCNGL.FUNC == 'N' 
                || BPCUCNGL.FUNC == 'S')) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.RC);
            } else {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCNGL.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUCNGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUCNGL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUCNGL = ");
            CEP.TRC(SCCGWA, BPCUCNGL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
