package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTICSS {
    String JIBS_tmp_str[] = new String[10];
    brParm BPTICSP_BR = new brParm();
    boolean pgmRtn = false;
    int WS_REC_LEN = 0;
    char WS_INPUT = '0';
    char WS_CALC = '1';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRICSP BPRICSP = new BPRICSP();
    SCCGWA SCCGWA;
    BPCRICSS BPCRICSS;
    BPRICSP BPRICSP1;
    public void MP(SCCGWA SCCGWA, BPCRICSS BPCRICSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRICSS = BPCRICSS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTICSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPRICSP1 = (BPRICSP) BPCRICSS.INFO.POINTER;
        IBS.init(SCCGWA, BPRICSP);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRICSP1);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRICSP1, BPRICSP);
        WS_INPUT = '0';
        WS_CALC = '1';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRICSS.INFO.FUNC == '1') {
            B011_STARTBR_RECORD_PROC_1();
            if (pgmRtn) return;
        } else if (BPCRICSS.INFO.FUNC == '6') {
            B017_STARTBR_RECORD_PROC_8();
            if (pgmRtn) return;
        } else if (BPCRICSS.INFO.FUNC == 'R') {
            B020_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCRICSS.INFO.FUNC == 'E') {
            B030_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCRICSS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRICSP);
        JIBS_tmp_str[1] = JIBS_tmp_str[0];
        IBS.CLONE(SCCGWA, BPRICSP, BPRICSP1);
    }
    public void B011_STARTBR_RECORD_PROC_1() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTICSP1();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTICSP1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
        CEP.TRC(SCCGWA, BPRICSP.CCY);
        BPTICSP_BR.rp = new DBParm();
        BPTICSP_BR.rp.TableName = "BPTICSP";
        BPTICSP_BR.rp.where = "CI_NO LIKE :BPRICSP.KEY.CI_NO "
            + "AND EXR_TYP LIKE :BPRICSP.EXR_TYP "
            + "AND CCY LIKE :BPRICSP.CCY";
        BPTICSP_BR.rp.order = "CI_NO,EXR_TYP,CCY";
        IBS.STARTBR(SCCGWA, BPRICSP, this, BPTICSP_BR);
    }
    public void B017_STARTBR_RECORD_PROC_8() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTICSP8();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTICSP();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTICSP();
        if (pgmRtn) return;
    }
    public void T000_READNEXT_BPTICSP() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRICSP, this, BPTICSP_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRICSS.INFO.RTN_INFO = 'Y';
            CEP.TRC(SCCGWA, BPRICSP.EXR_TYP);
            CEP.TRC(SCCGWA, BPRICSP.CCY);
            CEP.TRC(SCCGWA, BPRICSP.KEY.CI_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRICSS.INFO.RTN_INFO = 'N';
        } else {
        }
    }
    public void T000_STARTBR_BPTICSP8() throws IOException,SQLException,Exception {
        BPTICSP_BR.rp = new DBParm();
        BPTICSP_BR.rp.TableName = "BPTICSP";
        BPTICSP_BR.rp.where = "CI_NO = :BPRICSP.KEY.CI_NO "
            + "AND EXR_TYP = :BPRICSP.EXR_TYP "
            + "AND CCY = :BPRICSP.CCY";
        BPTICSP_BR.rp.order = "CI_NO DESC";
        IBS.STARTBR(SCCGWA, BPRICSP, this, BPTICSP_BR);
    }
    public void T000_ENDBR_BPTICSP() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTICSP_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRICSS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRICSS = ");
            CEP.TRC(SCCGWA, BPCRICSS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
