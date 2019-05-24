package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHITN {
    brParm BPTIHIT_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTIHIT = "BPTIHIT ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRIHIT BPRIHIT = new BPRIHIT();
    SCCGWA SCCGWA;
    BPCRHITN BPCRHITN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRHITN BPCRHITN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHITN = BPCRHITN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHITN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHITN.RC);
        IBS.init(SCCGWA, BPRIHIT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITN.BR;
        BPRIHIT.KEY.CCY = BPCRHITN.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITN.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITN.TENOR;
        BPRIHIT.KEY.DT = BPCRHITN.DT;
        T000_STARTBR_BPTIHIT();
        if (pgmRtn) return;
        T000_READNEXT_BPTIHIT();
        if (pgmRtn) return;
        T000_READNEXT_BPTIHIT();
        if (pgmRtn) return;
        BPCRHITN.DT = BPRIHIT.KEY.DT;
        BPCRHITN.HIS_DT = BPRIHIT.KEY.DT;
        T000_ENDBR_BPTIHIT();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTIHIT() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :BPRIHIT.KEY.DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITN.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READNEXT_BPTIHIT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITN.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_ENDBR_BPTIHIT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTIHIT_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHITN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHITN = ");
            CEP.TRC(SCCGWA, BPCRHITN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
