package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTFAVB {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTEXFAV_BR = new brParm();
    boolean pgmRtn = false;
    String PGM_BPZTFAVB = "BPZTFAVB";
    String TBL_BPTEXFAV = "BPTEXFAV";
    int WS_REC_LEN = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPREXFAV BPREXFAV = new BPREXFAV();
    SCCGWA SCCGWA;
    BPCRFAVB BPCRFAVB;
    BPREXFAV BPREXFAW;
    public void MP(SCCGWA SCCGWA, BPCRFAVB BPCRFAVB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRFAVB = BPCRFAVB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTFAVB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPREXFAW = (BPREXFAV) BPCRFAVB.INFO.POINTER;
        IBS.init(SCCGWA, BPREXFAV);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXFAW);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPREXFAW, BPREXFAV);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRFAVB.INFO.FUNC == 'S') {
            B010_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVB.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRFAVB.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRFAVB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPREXFAV);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPREXFAV, BPREXFAW);
    }
    public void B010_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTEXFAV1();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTEXFAV();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTEXFAV() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPREXFAV, this, BPTEXFAV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVB.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAVB.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTEXFAV1() throws IOException,SQLException,Exception {
        BPTEXFAV_BR.rp = new DBParm();
        BPTEXFAV_BR.rp.TableName = "BPTEXFAV";
        BPTEXFAV_BR.rp.where = "FAV_CODE LIKE :BPREXFAV.KEY.FAV_CODE "
            + "AND CCY LIKE :BPREXFAV.KEY.CCY";
        BPTEXFAV_BR.rp.order = "FAV_CODE";
        IBS.STARTBR(SCCGWA, BPREXFAV, this, BPTEXFAV_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRFAVB.INFO.RTN_INFO = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRFAVB.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTEXFAV() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTEXFAV_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRFAVB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRFAVB = ");
            CEP.TRC(SCCGWA, BPCRFAVB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
