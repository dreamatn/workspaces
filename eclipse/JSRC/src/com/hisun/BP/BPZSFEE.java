package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFEE {
    brParm BPTFCTR_BR = new brParm();
    brParm BPTFSCH_BR = new brParm();
    brParm BPTFSCHD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    double WS_FCTR_CHA_AMT = 0;
    double WS_FSCHD_CHA_AMT = 0;
    double WS_TOT_CHA_AMT = 0;
    char WS_FCTR_FLG = ' ';
    char WS_FSCH_FLG = ' ';
    char WS_FSCHD_FLG = ' ';
    BPRFCTR BPRFCTR = new BPRFCTR();
    BPRFSCH BPRFSCH = new BPRFSCH();
    BPRFSCHD BPRFSCHD = new BPRFSCHD();
    BPCFX BPCFX = new BPCFX();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGAPV SCCGAPV;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCFEE BPCFEE;
    public void MP(SCCGWA SCCGWA, BPCFEE BPCFEE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFEE = BPCFEE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFEE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_FCTR_INFO();
        if (pgmRtn) return;
        B020_GET_FSCH_INFO();
        if (pgmRtn) return;
        B030_COMP_TOT_AMT();
        if (pgmRtn) return;
    }
    public void B010_GET_FCTR_INFO() throws IOException,SQLException,Exception {
        S000_STARTBR_FCTR();
        if (pgmRtn) return;
        S000_READNEXT_FCTR();
        if (pgmRtn) return;
        while (WS_FCTR_FLG != 'N') {
            CEP.TRC(SCCGWA, BPRFCTR.CHARGE_CCY);
            CEP.TRC(SCCGWA, BPCFEE.CCY);
            if (!BPRFCTR.CHARGE_CCY.equalsIgnoreCase(BPCFEE.CCY)) {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.BUY_CCY = BPRFCTR.CHARGE_CCY;
                BPCFX.SELL_CCY = BPCFEE.CCY;
                BPCFX.FUNC = '3';
                BPCFX.BUY_AMT = BPRFCTR.CHARGE_AMT;
                BPCFX.EXR_TYPE = BPCRBANK.EX_RA;
                S000_CALL_BPZSFX();
                if (pgmRtn) return;
                WS_FCTR_CHA_AMT += BPCFX.SELL_AMT;
            } else {
                WS_FCTR_CHA_AMT += BPRFCTR.CHARGE_AMT;
            }
            S000_READNEXT_FCTR();
            if (pgmRtn) return;
        }
        S000_ENDBR_FCTR();
        if (pgmRtn) return;
    }
    public void S000_STARTBR_FCTR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFCTR);
        WS_FCTR_FLG = 'N';
        BPRFCTR.REL_CTRT_NO = BPCFEE.CON_NO;
        BPTFCTR_BR.rp = new DBParm();
        BPTFCTR_BR.rp.TableName = "BPTFCTR";
        BPTFCTR_BR.rp.where = "REL_CTRT_NO = :BPRFCTR.REL_CTRT_NO "
            + "AND FEE_STATUS IN ( '1' , '2' )";
        IBS.STARTBR(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
    }
    public void S000_READNEXT_FCTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFCTR, this, BPTFCTR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FCTR_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FCTR_FLG = 'N';
        } else {
        }
    }
    public void S000_ENDBR_FCTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFCTR_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B020_GET_FSCH_INFO() throws IOException,SQLException,Exception {
        S000_STARTBR_FSCH();
        if (pgmRtn) return;
        S000_READNEXT_FSCH();
        if (pgmRtn) return;
        while (WS_FSCH_FLG != 'N') {
            S000_STARTBR_FSCHD();
            if (pgmRtn) return;
            S000_READNEXT_FSCHD();
            if (pgmRtn) return;
            while (WS_FSCHD_FLG != 'N') {
                if (!BPRFCTR.CHARGE_CCY.equalsIgnoreCase(BPCFEE.CCY)) {
                    IBS.init(SCCGWA, BPCFX);
                    BPCFX.BUY_CCY = BPRFSCHD.CHARGE_CCY;
                    BPCFX.SELL_CCY = BPCFEE.CCY;
                    BPCFX.BUY_AMT = BPRFSCHD.CHARGE_AMT;
                    BPCFX.FUNC = '3';
                    S000_CALL_BPZSFX();
                    if (pgmRtn) return;
                    WS_FSCHD_CHA_AMT += BPCFX.SELL_AMT;
                } else {
                    WS_FSCHD_CHA_AMT += BPRFSCHD.CHARGE_AMT;
                }
                S000_READNEXT_FSCHD();
                if (pgmRtn) return;
            }
            S000_ENDBR_FSCHD();
            if (pgmRtn) return;
            S000_READNEXT_FSCH();
            if (pgmRtn) return;
        }
        S000_ENDBR_FSCH();
        if (pgmRtn) return;
    }
    public void S000_STARTBR_FSCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCH);
        WS_FSCH_FLG = 'N';
        BPRFSCH.REL_CTRT_NO = BPCFEE.CON_NO;
        BPTFSCH_BR.rp = new DBParm();
        BPTFSCH_BR.rp.TableName = "BPTFSCH";
        BPTFSCH_BR.rp.where = "REL_CTRT_NO = :BPRFSCH.REL_CTRT_NO "
            + "AND FEE_STATUS IN ( '1' , '2' )";
        IBS.STARTBR(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        CEP.TRC(SCCGWA, BPRFSCH.KEY.CTRT_NO);
    }
    public void S000_READNEXT_FSCH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFSCH, this, BPTFSCH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FSCH_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FSCH_FLG = 'N';
        } else {
        }
    }
    public void S000_ENDBR_FSCH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFSCH_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S000_STARTBR_FSCHD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFSCHD);
        WS_FSCHD_FLG = 'N';
        BPRFSCHD.KEY.CTRT_NO = BPRFSCH.KEY.CTRT_NO;
        BPTFSCHD_BR.rp = new DBParm();
        BPTFSCHD_BR.rp.TableName = "BPTFSCHD";
        BPTFSCHD_BR.rp.where = "CTRT_NO = :BPRFSCHD.KEY.CTRT_NO";
        IBS.STARTBR(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
    }
    public void S000_READNEXT_FSCHD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRFSCHD, this, BPTFSCHD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FSCHD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FSCHD_FLG = 'N';
        } else {
        }
    }
    public void S000_ENDBR_FSCHD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTFSCHD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B030_COMP_TOT_AMT() throws IOException,SQLException,Exception {
        WS_TOT_CHA_AMT = WS_FCTR_CHA_AMT + WS_FSCHD_CHA_AMT;
        BPCFEE.TOT_AMT = WS_TOT_CHA_AMT;
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
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
