package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMFRL {
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITFLMT_RD;
    DBParm CITFLRL_RD;
    brParm CITACR_BR = new brParm();
    brParm CITFLRL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRFLMT CIRFLMT = new CIRFLMT();
    CIRFLRL CIRFLRL = new CIRFLRL();
    CIRFLRL CIRFLRLO = new CIRFLRL();
    CIRFLRL CIRFLRLN = new CIRFLRL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICOMFRL CICOMFRL = new CICOMFRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMFRL CICMFRL;
    public void MP(SCCGWA SCCGWA, CICMFRL CICMFRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMFRL = CICMFRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMFRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICMFRL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICMFRL.FUNC == 'B') {
            B020_BRW_FLRL_INF();
            if (pgmRtn) return;
        } else if (CICMFRL.FUNC == 'A') {
            B030_ADD_FLRL_INF();
            if (pgmRtn) return;
        } else if (CICMFRL.FUNC == 'D') {
            B040_DEL_FLRL_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICMFRL.DATA.CI_NO);
        if (CICMFRL.DATA.CI_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICMFRL.DATA.CI_NO;
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (CIRBAS.STSW == null) CIRBAS.STSW = "";
            JIBS_tmp_int = CIRBAS.STSW.length();
            for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
                || CIRBAS.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
                || CIRBAS.STSW.substring(23 - 1, 23 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "BAS INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICMFRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (CICMFRL.DATA.SVS_ADC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRFLMT);
            CIRFLMT.KEY.SVS_ADC_NO = CICMFRL.DATA.SVS_ADC_NO;
            CEP.TRC(SCCGWA, CIRFLMT.KEY.SVS_ADC_NO);
            T000_READ_CITFLMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "FLMT INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICMFRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_BRW_FLRL_INF() throws IOException,SQLException,Exception {
        if (CICMFRL.DATA.CI_NO.trim().length() > 0) {
            B021_BRW_FLRL_INF_BY_CI();
            if (pgmRtn) return;
        } else if (CICMFRL.DATA.SVS_ADC_NO.trim().length() > 0) {
            B022_BRW_FLRL_INF_BY_ADC();
            if (pgmRtn) return;
        } else if (CICMFRL.DATA.AGR_NO.trim().length() > 0) {
            B023_BRW_FLRL_INF_BY_AGR();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B021_BRW_FLRL_INF_BY_CI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICMFRL.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICMFRL.DATA.CI_NO);
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        T000_STARTBR_CITACR();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRFLRL);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.STS);
            CIRFLRL.KEY.AGR_NO = CIRACR.KEY.AGR_NO;
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            T000_STARTBR_CITFLRL_BY_ACADC();
            if (pgmRtn) return;
            T000_READNEXT_CITFLRL();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                CEP.TRC(SCCGWA, "HERE");
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
                T000_READNEXT_CITFLRL();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITFLRL();
            if (pgmRtn) return;
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
    }
    public void B022_BRW_FLRL_INF_BY_ADC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRL);
        CIRFLRL.KEY.SVS_ADC_NO = CICMFRL.DATA.SVS_ADC_NO;
        T000_STARTBR_CITFLRL_BY_ACADC();
        if (pgmRtn) return;
        T000_READNEXT_CITFLRL();
        if (pgmRtn) return;
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CIRFLRL.KEY.AGR_NO;
            CEP.TRC(SCCGWA, CIRFLRL.KEY.AGR_NO);
            CEP.TRC(SCCGWA, CIRACR.KEY.AGR_NO);
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                B020_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITFLRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITFLRL();
        if (pgmRtn) return;
    }
    public void B023_BRW_FLRL_INF_BY_AGR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRL);
        IBS.init(SCCGWA, CIRACR);
        CIRFLRL.KEY.AGR_NO = CICMFRL.DATA.AGR_NO;
        CIRACR.KEY.AGR_NO = CICMFRL.DATA.AGR_NO;
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_STARTBR_CITFLRL_BY_ACADC();
        if (pgmRtn) return;
        T000_READNEXT_CITFLRL();
        if (pgmRtn) return;
        B020_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            B020_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITFLRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITFLRL();
        if (pgmRtn) return;
    }
    public void B030_ADD_FLRL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLMT);
        IBS.init(SCCGWA, CIRFLRL);
        IBS.init(SCCGWA, CIRACR);
        if (CICMFRL.DATA.SVS_ADC_NO.trim().length() == 0 
            || CICMFRL.DATA.AGR_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "核准件号和客户账号必须全�?");
        }
        CIRFLRL.KEY.SVS_ADC_NO = CICMFRL.DATA.SVS_ADC_NO;
        CIRFLMT.KEY.SVS_ADC_NO = CICMFRL.DATA.SVS_ADC_NO;
        CIRFLRL.KEY.AGR_NO = CICMFRL.DATA.AGR_NO;
        CIRACR.KEY.AGR_NO = CICMFRL.DATA.AGR_NO;
        T000_READ_CITFLMT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READ_CITACR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRFLMT.CI_NO);
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        if (!CIRFLMT.CI_NO.equalsIgnoreCase(CIRACR.CI_NO)) {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_REL_CINO_NOEQ, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READ_CITFLRL_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (CIRFLRL.REL_STS == '1') {
                IBS.CLONE(SCCGWA, CIRFLRL, CIRFLRLO);
                CIRFLRL.REL_STS = '0';
                CIRFLRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRFLRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRFLRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITFLRL();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRFLRL, CIRFLRLN);
            } else {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLRL_EXIST, CICMFRL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            CIRFLRL.REL_STS = '0';
            CIRFLRL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRFLRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRFLRL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRFLRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRFLRL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRFLRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITFLRL();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRFLRL, CIRFLRLN);
        }
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B040_DEL_FLRL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRL);
        IBS.init(SCCGWA, CIRACR);
        CIRFLRL.KEY.SVS_ADC_NO = CICMFRL.DATA.SVS_ADC_NO;
        CIRFLRL.KEY.AGR_NO = CICMFRL.DATA.AGR_NO;
        CEP.TRC(SCCGWA, CICMFRL.DATA.SVS_ADC_NO);
        CEP.TRC(SCCGWA, CIRFLRL.KEY.SVS_ADC_NO);
        CEP.TRC(SCCGWA, CICMFRL.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CIRFLRL.KEY.SVS_ADC_NO);
        T000_READ_CITFLRL_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLRL_NOTFND, CICMFRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRFLRL, CIRFLRLO);
        CIRFLRL.REL_STS = '1';
        CIRFLRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITFLRL();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFLRL, CIRFLRLN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_STS;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B020_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        R000_DATA_TRANS_TO_FMTMPAG();
        if (pgmRtn) return;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOMFRL);
        SCCMPAG.DATA_LEN = 115;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_FMTMPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOMFRL);
        CEP.TRC(SCCGWA, CIRACR.CI_NO);
        CEP.TRC(SCCGWA, CICOMFRL.DATA.CI_NO);
        CICOMFRL.DATA.CI_NO = CIRACR.CI_NO;
        CICOMFRL.DATA.SVS_ADC_NO = CIRFLRL.KEY.SVS_ADC_NO;
        CICOMFRL.DATA.AGR_NO = CIRFLRL.KEY.AGR_NO;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRFLRL";
        BPCPNHIS.INFO.FMT_ID_LEN = 173;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRFLRLO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRFLRLN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        CITACR_RD.where = "AGR_NO = :CIRACR.KEY.AGR_NO "
            + "AND STS < > '1'";
        IBS.READ(SCCGWA, CIRACR, this, CITACR_RD);
    }
    public void T000_READ_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_READ_CITFLRL() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        IBS.READ(SCCGWA, CIRFLRL, CITFLRL_RD);
    }
    public void T000_STARTBR_CITACR() throws IOException,SQLException,Exception {
        CITACR_BR.rp = new DBParm();
        CITACR_BR.rp.TableName = "CITACR";
        CITACR_BR.rp.where = "CI_NO = :CIRACR.CI_NO "
            + "AND STS < > '1'";
        IBS.STARTBR(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_READNEXT_CITACR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACR, this, CITACR_BR);
    }
    public void T000_ENDBR_CITACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACR_BR);
    }
    public void T000_READ_CITFLRL_UPD() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        CITFLRL_RD.upd = true;
        IBS.READ(SCCGWA, CIRFLRL, CITFLRL_RD);
    }
    public void T000_STARTBR_CITFLRL_BY_ACADC() throws IOException,SQLException,Exception {
        CITFLRL_BR.rp = new DBParm();
        CITFLRL_BR.rp.TableName = "CITFLRL";
        CITFLRL_BR.rp.where = "( AGR_NO = :CIRFLRL.KEY.AGR_NO "
            + "OR SVS_ADC_NO = :CIRFLRL.KEY.SVS_ADC_NO ) "
            + "AND REL_STS = '0'";
        IBS.STARTBR(SCCGWA, CIRFLRL, this, CITFLRL_BR);
    }
    public void T000_READNEXT_CITFLRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRFLRL, this, CITFLRL_BR);
    }
    public void T000_ENDBR_CITFLRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITFLRL_BR);
    }
    public void T000_WRITE_CITFLRL() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        IBS.WRITE(SCCGWA, CIRFLRL, CITFLRL_RD);
    }
    public void T000_REWRITE_CITFLRL() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        IBS.REWRITE(SCCGWA, CIRFLRL, CITFLRL_RD);
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
