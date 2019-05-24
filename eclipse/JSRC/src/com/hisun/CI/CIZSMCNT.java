package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMCNT {
    DBParm CITRISK_RD;
    DBParm CITBAS_RD;
    brParm CITCNT_BR = new brParm();
    DBParm CITCNT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 0;
    int K_COL_CNT = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCNT CIRCNT = new CIRCNT();
    CIRCNT CIRCNTN = new CIRCNT();
    CIRCNT CIRCNTO = new CIRCNT();
    CIRRISK CIRRISK = new CIRRISK();
    CICOCNT CICOCNT = new CICOCNT();
    CICCGHIS CICCGHIS = new CICCGHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMCNT CICSMCNT;
    public void MP(SCCGWA SCCGWA, CICSMCNT CICSMCNT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMCNT = CICSMCNT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMCNT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMCNT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICSMCNT.FUNC);
        if (CICSMCNT.FUNC == 'B') {
            B020_BRW_CNT_INF();
            if (pgmRtn) return;
        } else if (CICSMCNT.FUNC == 'A') {
            B030_ADD_CNT_INF();
            if (pgmRtn) return;
        } else if (CICSMCNT.FUNC == 'M') {
            B040_MOD_CNT_INF();
            if (pgmRtn) return;
        } else if (CICSMCNT.FUNC == 'D') {
            B050_DEL_CNT_INF();
            if (pgmRtn) return;
        } else if (CICSMCNT.FUNC == 'I') {
            B060_INQ_CNT_INF();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMCNT.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICSMCNT.DATA.CI_NO);
        if (CICSMCNT.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI NO MUST INPUT");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP == '3' 
            && (CICSMCNT.FUNC == 'A' 
            || CICSMCNT.FUNC == 'M' 
            || CICSMCNT.FUNC == 'D')) {
            CEP.TRC(SCCGWA, CIRBAS.OPN_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != CIRBAS.OPN_BR 
                && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != SCCGWA.COMM_AREA.HQT_BANK) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_CUST_CANT_MOD);
            }
        }
    }
    public void B020_BRW_CNT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        T000_STARTBR_CITCNT();
        if (pgmRtn) return;
        T000_READNEXT_CITCNT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CNT INF NOT FND");
            Z_RET();
            if (pgmRtn) return;
        }
        R000_01_OUT_TITLE();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRCNT);
            IBS.init(SCCGWA, CICOCNT);
            CICOCNT.DATA.CNT_TYPE = CIRCNT.KEY.CNT_TYPE;
            CICOCNT.DATA.CNT_CNTY = CIRCNT.CNT_CNTY;
            CICOCNT.DATA.CNT_TECN = CIRCNT.TEL_CNTY;
            CICOCNT.DATA.CNT_ZONE = CIRCNT.CNT_ZONE;
            CICOCNT.DATA.CNT_TEL = CIRCNT.TEL_NO;
            CICOCNT.DATA.CNT_TEL2 = CIRCNT.TEL_NO1;
            CICOCNT.DATA.CNT_INFO = CIRCNT.CNT_INFO;
            CEP.TRC(SCCGWA, CICOCNT);
            R000_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITCNT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCNT();
        if (pgmRtn) return;
    }
    public void B030_ADD_CNT_INF() throws IOException,SQLException,Exception {
        if (CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("21") 
            || CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("11") 
            || CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("12") 
            || CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("13")) {
            if (CICSMCNT.DATA.CNT_TEL.trim().length() == 0) {
                CEP.TRC(SCCGWA, "TEL NO MUST INPUT");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "电话类必须输入电话号�?");
            }
        } else {
            if (CICSMCNT.DATA.CNT_INFO.trim().length() == 0) {
                CEP.TRC(SCCGWA, "CNT INFO MUST INPUT");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "非电话类必须输入具体联系�?");
            }
        }
        if (CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("21")) {
            CEP.TRC(SCCGWA, "CHECK SAFE TELEPHONE");
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
            CIRCNT.TEL_NO = CICSMCNT.DATA.CNT_TEL;
            T000_READ_CITCNT_CHK_TEL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRCNT.KEY.CI_NO);
                if (!CIRCNT.KEY.CI_NO.equalsIgnoreCase(CICSMCNT.DATA.CI_NO)) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TEL_NO_EXIST);
                }
            }
        }
        IBS.init(SCCGWA, CIRCNT);
        IBS.init(SCCGWA, CIRCNTN);
        IBS.init(SCCGWA, CIRCNTO);
        CIRCNT.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
        T000_READ_CITCNT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_INF_EXIST, CICSMCNT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CIRCNT.CNT_CNTY = CICSMCNT.DATA.CNT_CNTY;
        CIRCNT.TEL_CNTY = CICSMCNT.DATA.CNT_TECN;
        CIRCNT.CNT_ZONE = CICSMCNT.DATA.CNT_ZONE;
        CIRCNT.TEL_NO = CICSMCNT.DATA.CNT_TEL;
        CIRCNT.TEL_NO1 = CICSMCNT.DATA.CNT_TEL2;
        CIRCNT.CNT_INFO = CICSMCNT.DATA.CNT_INFO;
        CIRCNT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCNT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCNT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCNT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCNT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCNT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITCNT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCNT, CIRCNTN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITCNT";
        CICCGHIS.DATA.OLD_DATA_LEN = 569;
        CICCGHIS.DATA.NEW_DATA_LEN = 569;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRCNTO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRCNTN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B040_MOD_CNT_INF() throws IOException,SQLException,Exception {
        if (CICSMCNT.DATA.CNT_TYPE.equalsIgnoreCase("21")) {
            CEP.TRC(SCCGWA, "CHECK SAFE TELEPHONE");
            IBS.init(SCCGWA, CIRCNT);
            CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
            CIRCNT.TEL_NO = CICSMCNT.DATA.CNT_TEL;
            T000_READ_CITCNT_CHK_TEL();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, CIRCNT.KEY.CI_NO);
                if (!CIRCNT.KEY.CI_NO.equalsIgnoreCase(CICSMCNT.DATA.CI_NO)) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TEL_NO_EXIST);
                }
            }
        }
        IBS.init(SCCGWA, CIRCNT);
        IBS.init(SCCGWA, CIRCNTN);
        IBS.init(SCCGWA, CIRCNTO);
        CIRCNT.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
        T000_READ_CITCNT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCNT, CIRCNTO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_NOTFND, CICSMCNT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B041_FATCA_CHK_INFO();
        if (pgmRtn) return;
        CIRCNT.CNT_CNTY = CICSMCNT.DATA.CNT_CNTY;
        CIRCNT.TEL_CNTY = CICSMCNT.DATA.CNT_TECN;
        CIRCNT.CNT_ZONE = CICSMCNT.DATA.CNT_ZONE;
        CIRCNT.TEL_NO = CICSMCNT.DATA.CNT_TEL;
        CIRCNT.TEL_NO1 = CICSMCNT.DATA.CNT_TEL2;
        CIRCNT.CNT_INFO = CICSMCNT.DATA.CNT_INFO;
        CIRCNT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCNT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCNT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITCNT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCNT, CIRCNTN);
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITCNT";
        CICCGHIS.DATA.OLD_DATA_LEN = 569;
        CICCGHIS.DATA.NEW_DATA_LEN = 569;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRCNTO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRCNTN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B041_FATCA_CHK_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRISK);
        CIRRISK.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        T000_READ_CITRISK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRCNT.CNT_CNTY);
        CEP.TRC(SCCGWA, CIRCNT.TEL_CNTY);
        CEP.TRC(SCCGWA, CICSMCNT.DATA.CNT_CNTY);
        CEP.TRC(SCCGWA, CICSMCNT.DATA.CNT_TECN);
        CEP.TRC(SCCGWA, CIRRISK.FATCA_TP);
        if ((!CIRCNT.TEL_CNTY.equalsIgnoreCase("USA") 
            && CICSMCNT.DATA.CNT_TECN.equalsIgnoreCase("USA") 
            && CIRRISK.FATCA_TP.equalsIgnoreCase("W800")) 
            || (CIRCNT.TEL_CNTY.equalsIgnoreCase("USA") 
            && !CICSMCNT.DATA.CNT_TECN.equalsIgnoreCase("USA") 
            && CIRRISK.FATCA_TP.equalsIgnoreCase("W900"))) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MOD_FATCA_TP);
        }
    }
    public void B050_DEL_CNT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        IBS.init(SCCGWA, CIRCNTN);
        IBS.init(SCCGWA, CIRCNTO);
        CIRCNT.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
        T000_READ_CITCNT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCNT, CIRCNTO);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_NOTFND, CICSMCNT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_DELETE_CITCNT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCGHIS);
        CICCGHIS.DATA.TABLE_NM = "CITCNT";
        CICCGHIS.DATA.OLD_DATA_LEN = 569;
        CICCGHIS.DATA.NEW_DATA_LEN = 569;
        CICCGHIS.DATA.OLD_DATA_PTR = CIRCNTO;
        CICCGHIS.DATA.NEW_DATA_PTR = CIRCNTN;
        S000_CALL_CIZCGHIS();
        if (pgmRtn) return;
    }
    public void B060_INQ_CNT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CICSMCNT.DATA.CI_NO;
        CIRCNT.KEY.CNT_TYPE = CICSMCNT.DATA.CNT_TYPE;
        T000_READ_CITCNT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICSMCNT.RLT_FLG = 'Y';
            CICSMCNT.DATA.CNT_CNTY = CIRCNT.CNT_CNTY;
            CICSMCNT.DATA.CNT_TECN = CIRCNT.TEL_CNTY;
            CICSMCNT.DATA.CNT_ZONE = CIRCNT.CNT_ZONE;
            CICSMCNT.DATA.CNT_TEL = CIRCNT.TEL_NO;
            CICSMCNT.DATA.CNT_TEL2 = CIRCNT.TEL_NO1;
            CICSMCNT.DATA.CNT_INFO = CIRCNT.CNT_INFO;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CNT INQUIRE NOT FIND");
            CICSMCNT.RLT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITCNT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void R000_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOCNT);
        SCCMPAG.DATA_LEN = 103;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCGHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MOD-HISTORY", CICCGHIS);
        if (CICCGHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCGHIS.RC);
        }
    }
    public void T000_READ_CITRISK() throws IOException,SQLException,Exception {
        CITRISK_RD = new DBParm();
        CITRISK_RD.TableName = "CITRISK";
        IBS.READ(SCCGWA, CIRRISK, CITRISK_RD);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_BR.rp = new DBParm();
        CITCNT_BR.rp.TableName = "CITCNT";
        CITCNT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRCNT, CITCNT_BR);
    }
    public void T000_READNEXT_CITCNT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCNT, this, CITCNT_BR);
    }
    public void T000_ENDBR_CITCNT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCNT_BR);
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITCNT_UPD() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.upd = true;
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_WRITE_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, CIRCNT, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "联系类型录入重复");
        }
    }
    public void T000_REWRITE_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.REWRITE(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_DELETE_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        IBS.DELETE(SCCGWA, CIRCNT, CITCNT_RD);
    }
    public void T000_READ_CITCNT_CHK_TEL() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.eqWhere = "CNT_TYPE,TEL_NO";
        CITCNT_RD.fst = true;
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
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
