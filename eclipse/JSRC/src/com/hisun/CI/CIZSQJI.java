package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQJI {
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITJRL_RD;
    brParm CITJRL_BR = new brParm();
    DBParm CITCNT_RD;
    DBParm CITADR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    String WS_CI_NO = " ";
    int WS_I = 0;
    int WS_PAGE_ROW = 0;
    int WS_CURRENT_ROW = 0;
    int WS_MIN_ROW = 0;
    int WS_MAX_ROW = 0;
    int WS_RECORD_NUM = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRJRL CIRJRL = new CIRJRL();
    CIRACR CIRACR = new CIRACR();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CICFB41 CICFB41 = new CICFB41();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQJI CICSQJI;
    public void MP(SCCGWA SCCGWA, CICSQJI CICSQJI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQJI = CICSQJI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQJI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQJI.RC);
        IBS.init(SCCGWA, CICFB41);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMPUTE_OUTPUT_ROW();
        if (pgmRtn) return;
        if (CICSQJI.FUNC == '1') {
            B030_01_INQ_CIRBAS_BY_HCI();
            if (pgmRtn) return;
        } else if (CICSQJI.FUNC == '2') {
            B030_02_INQ_CIRBAS_BY_JCI();
            if (pgmRtn) return;
        } else if (CICSQJI.FUNC == '3') {
            B030_03_INQ_CIRBAS_BY_AGR();
            if (pgmRtn) return;
        }
        B040_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRACR);
        if (CICSQJI.FUNC == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CICSQJI.FUNC == '1') {
            if (CICSQJI.DATA.CI_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名户主客户必输");
            } else {
                CIRBAS.KEY.CI_NO = CICSQJI.DATA.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
                }
            }
        } else if (CICSQJI.FUNC == '2') {
            if (CICSQJI.DATA.JCI_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名客户必输");
            } else {
                CIRBAS.KEY.CI_NO = CICSQJI.DATA.JCI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
                }
            }
        } else if (CICSQJI.FUNC == '3') {
            if (CICSQJI.DATA.AGR_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名账号必输");
            } else {
                CIRACR.KEY.AGR_NO = CICSQJI.DATA.AGR_NO;
                T000_READ_CITACR();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACINFO_NOEXIST);
                }
            }
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B020_COMPUTE_OUTPUT_ROW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSQJI.DATA.PAGE_ROW);
        if (CICSQJI.DATA.PAGE_ROW > K_MAX_ROW) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSQJI.DATA.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, CICSQJI.DATA.PAGE_NUM);
        WS_MIN_ROW = ( CICSQJI.DATA.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
        CEP.TRC(SCCGWA, WS_MIN_ROW);
        WS_MAX_ROW = CICSQJI.DATA.PAGE_NUM * WS_PAGE_ROW;
        CEP.TRC(SCCGWA, WS_MAX_ROW);
        WS_CURRENT_ROW = 1;
        WS_I = 1;
    }
    public void B030_01_INQ_CIRBAS_BY_HCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.HCI_NO = CICSQJI.DATA.CI_NO;
        T000_STARTBR_CITJRL_BYHCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYHCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, "联名户主客户不存�?");
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            WS_CI_NO = " ";
            WS_CI_NO = CIRJRL.KEY.JCI_NO;
            IBS.init(SCCGWA, CIRJRL);
            CIRJRL.KEY.JCI_NO = WS_CI_NO;
            T000_STARTBR_CITJRL_BYJCI();
            if (pgmRtn) return;
            T000_READNEXT_CITJRL_BYJCI();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRJRL.KEY.HCI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, WS_CURRENT_ROW);
                if (WS_CURRENT_ROW >= WS_MIN_ROW 
                    && WS_CURRENT_ROW <= WS_MAX_ROW 
                    && WS_I <= WS_PAGE_ROW) {
                    R000_TRANS_DATA_TO_OUTPUT();
                    if (pgmRtn) return;
                }
                WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
                T000_READNEXT_CITJRL_BYJCI();
                if (pgmRtn) return;
            }
            T000_ENDBR_CITJRL_BYJCI();
            if (pgmRtn) return;
            T000_READNEXT_CITJRL_BYHCI();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL_BYHCI();
        if (pgmRtn) return;
    }
    public void B030_02_INQ_CIRBAS_BY_JCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.JCI_NO = CICSQJI.DATA.JCI_NO;
        T000_STARTBR_CITJRL_BYJCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYJCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_NOT_EXIST);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRJRL.KEY.HCI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CURRENT_ROW);
            if (WS_CURRENT_ROW >= WS_MIN_ROW 
                && WS_CURRENT_ROW <= WS_MAX_ROW 
                && WS_I <= WS_PAGE_ROW) {
                R000_TRANS_DATA_TO_OUTPUT();
                if (pgmRtn) return;
            }
            WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            T000_READNEXT_CITJRL_BYJCI();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL_BYJCI();
        if (pgmRtn) return;
    }
    public void B030_03_INQ_CIRBAS_BY_AGR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.JCI_NO = CIRACR.CI_NO;
        T000_STARTBR_CITJRL_BYJCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYJCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_NOT_EXIST);
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRJRL.KEY.HCI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CURRENT_ROW);
            if (WS_CURRENT_ROW >= WS_MIN_ROW 
                && WS_CURRENT_ROW <= WS_MAX_ROW 
                && WS_I <= WS_PAGE_ROW) {
                R000_TRANS_DATA_TO_OUTPUT();
                if (pgmRtn) return;
            }
            WS_CURRENT_ROW = WS_CURRENT_ROW + 1;
            T000_READNEXT_CITJRL_BYJCI();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL_BYJCI();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_I);
        CICFB41.DATA[WS_I-1].JCI_NO = CIRJRL.KEY.JCI_NO;
        CICFB41.DATA[WS_I-1].CI_NO = CIRBAS.KEY.CI_NO;
        CICFB41.DATA[WS_I-1].ID_TYPE = CIRBAS.ID_TYPE;
        CICFB41.DATA[WS_I-1].ID_NO = CIRBAS.ID_NO;
        CICFB41.DATA[WS_I-1].CI_NM = CIRBAS.CI_NM;
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRCNT.KEY.CNT_TYPE = "21";
        T000_READ_CITCNT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB41.DATA[WS_I-1].TEL_NO = CIRCNT.TEL_NO;
        }
        IBS.init(SCCGWA, CIRADR);
        CIRADR.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRADR.KEY.ADR_TYPE = "111";
        T000_READ_CITADR();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB41.DATA[WS_I-1].ADDR_NM = CIRADR.ADR_NM;
        }
        WS_I = WS_I + 1;
    }
    public void B040_OUTPUT_DATA() throws IOException,SQLException,Exception {
        WS_CURRENT_ROW = WS_CURRENT_ROW - 1;
        WS_I = WS_I - 1;
        CICFB41.TOTAL_NUM = WS_CURRENT_ROW;
        WS_RECORD_NUM = WS_CURRENT_ROW % WS_PAGE_ROW;
        CICFB41.TOTAL_PAGE = (int) ((WS_CURRENT_ROW - WS_RECORD_NUM) / WS_PAGE_ROW);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        if (WS_RECORD_NUM > 0) {
            CICFB41.TOTAL_PAGE = CICFB41.TOTAL_PAGE + 1;
        }
        CICFB41.CURR_PAGE = CICSQJI.DATA.PAGE_NUM;
        CICFB41.PAGE_ROW = WS_I;
        if (CICFB41.CURR_PAGE >= CICFB41.TOTAL_PAGE 
            || CICFB41.TOTAL_PAGE == 0) {
            CICFB41.LAST_PAGE = 'Y';
        } else {
            CICFB41.LAST_PAGE = 'N';
        }
        CEP.TRC(SCCGWA, CICFB41.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICFB41.CURR_PAGE);
        CEP.TRC(SCCGWA, CICFB41.PAGE_ROW);
        CEP.TRC(SCCGWA, CICFB41.LAST_PAGE);
        CEP.TRC(SCCGWA, CICFB41.DATA[1-1].CI_NO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIB41";
        SCCFMT.DATA_PTR = CICFB41;
        SCCFMT.DATA_LEN = 5745;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITJRL() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        IBS.READ(SCCGWA, CIRJRL, CITJRL_RD);
    }
    public void T000_STARTBR_CITJRL_BYHCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp = new DBParm();
        CITJRL_BR.rp.TableName = "CITJRL";
        CITJRL_BR.rp.Reqid = 1;
        CITJRL_BR.rp.eqWhere = "HCI_NO";
        CITJRL_BR.rp.order = "JCI_NO";
        IBS.STARTBR(SCCGWA, CIRJRL, CITJRL_BR);
    }
    public void T000_READNEXT_CITJRL_BYHCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp.Reqid = 1;
        IBS.READNEXT(SCCGWA, CIRJRL, this, CITJRL_BR);
    }
    public void T000_ENDBR_CITJRL_BYHCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp.Reqid = 1;
        IBS.ENDBR(SCCGWA, CITJRL_BR);
    }
    public void T000_STARTBR_CITJRL_BYJCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp = new DBParm();
        CITJRL_BR.rp.TableName = "CITJRL";
        CITJRL_BR.rp.Reqid = 2;
        CITJRL_BR.rp.eqWhere = "JCI_NO";
        CITJRL_BR.rp.order = "HCI_NO";
        IBS.STARTBR(SCCGWA, CIRJRL, CITJRL_BR);
    }
    public void T000_READNEXT_CITJRL_BYJCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp.Reqid = 2;
        IBS.READNEXT(SCCGWA, CIRJRL, this, CITJRL_BR);
    }
    public void T000_ENDBR_CITJRL_BYJCI() throws IOException,SQLException,Exception {
        CITJRL_BR.rp.Reqid = 2;
        IBS.ENDBR(SCCGWA, CITJRL_BR);
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        IBS.READ(SCCGWA, CIRADR, CITADR_RD);
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
