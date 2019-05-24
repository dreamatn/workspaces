package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQJC {
    DBParm CITBAS_RD;
    DBParm CITACR_RD;
    DBParm CITJRL_RD;
    brParm CITJRL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_CNT = 0;
    String WS_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRJRL CIRJRL = new CIRJRL();
    CIRACR CIRACR = new CIRACR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICCINO CICCINO = new CICCINO();
    CICOSQJC CICOSQJC = new CICOSQJC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQJC CICSQJC;
    public void MP(SCCGWA SCCGWA, CICSQJC CICSQJC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQJC = CICSQJC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQJC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQJC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSQJC.FUNC == '1') {
            B020_01_INQ_CIRBAS_BY_HCI();
            if (pgmRtn) return;
        } else if (CICSQJC.FUNC == '2') {
            B020_02_INQ_CIRBAS_BY_JCI();
            if (pgmRtn) return;
        } else if (CICSQJC.FUNC == '3') {
            B020_03_INQ_CIRBAS_BY_AGR();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRACR);
        if (CICSQJC.FUNC == ' ') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        if (CICSQJC.FUNC == '1') {
            if (CICSQJC.DATA.CI_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名户主客户必输");
            } else {
                CIRBAS.KEY.CI_NO = CICSQJC.DATA.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
                }
            }
        } else if (CICSQJC.FUNC == '2') {
            if (CICSQJC.DATA.JCI_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名客户必输");
            } else {
                CIRBAS.KEY.CI_NO = CICSQJC.DATA.JCI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_NOTFND);
                }
            }
        } else if (CICSQJC.FUNC == '3') {
            if (CICSQJC.DATA.AGR_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "联名账号必输");
            } else {
                CIRACR.KEY.AGR_NO = CICSQJC.DATA.AGR_NO;
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
    public void B020_01_INQ_CIRBAS_BY_HCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.HCI_NO = CICSQJC.DATA.CI_NO;
        T000_STARTBR_CITJRL_BYHCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYHCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_NOT_EXIST, "联名户主客户不存�?");
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
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
                R000_02_OUT_BRW_DATA();
                if (pgmRtn) return;
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
    public void B020_02_INQ_CIRBAS_BY_JCI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.JCI_NO = CICSQJC.DATA.JCI_NO;
        T000_STARTBR_CITJRL_BYJCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYJCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_NOT_EXIST);
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRJRL.KEY.HCI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITJRL_BYJCI();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL_BYJCI();
        if (pgmRtn) return;
    }
    public void B020_03_INQ_CIRBAS_BY_AGR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRJRL);
        CIRJRL.KEY.JCI_NO = CIRACR.CI_NO;
        T000_STARTBR_CITJRL_BYJCI();
        if (pgmRtn) return;
        T000_READNEXT_CITJRL_BYJCI();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_JRL_INF_NOT_EXIST);
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CIRJRL.KEY.HCI_NO;
            T000_READ_CITBAS();
            if (pgmRtn) return;
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITJRL_BYJCI();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITJRL_BYJCI();
        if (pgmRtn) return;
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = 99;
        SCCMPAG.SCR_ROW_CNT = 30;
        SCCMPAG.SCR_COL_CNT = 9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        R000_DATA_TRANS_TO_MPAG();
        if (pgmRtn) return;
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOSQJC);
        SCCMPAG.DATA_LEN = 353;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_MPAG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSQJC);
        CICOSQJC.DATA.JCI_NO = CIRJRL.KEY.JCI_NO;
        CICOSQJC.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOSQJC.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICOSQJC.DATA.ID_NO = CIRBAS.ID_NO;
        CICOSQJC.DATA.CI_NM = CIRBAS.CI_NM;
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
